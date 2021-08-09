package baobao.zerenlianmoshi.dubbo;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.utils.NetUtils;
import org.apache.dubbo.rpc.*;

import static baobao.Constant.APP_NAME;
import static baobao.Constant.CONSUMER_APP_NAME;
import static org.apache.dubbo.common.constants.CommonConstants.*;
import static org.apache.dubbo.common.utils.StringUtils.isNotEmpty;

@Activate(group = CONSUMER,order = -10000)
public class ConsumerContextFilter extends ListenableFilter {

    public ConsumerContextFilter(){
        super.listener = new ConsumerContextListener();
    }
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        RpcContext.getContext().setInvoker(invoker)
                .setInvocation(invocation)
                .setLocalAddress(NetUtils.getLocalHost(),0)
                .setRemoteAddress(invoker.getUrl().getHost(),invoker.getUrl().getPort())
                .setRemoteApplicationName(invoker.getUrl().getParameter(REMOTE_APPLICATION_KEY))
                .setAttachment(REMOTE_APPLICATION_KEY,invoker.getUrl().getParameter(APPLICATION_KEY));
        if(invocation instanceof  RpcInvocation){
            ((RpcInvocation) invocation).setInvoker(invoker);
        }

        // 这里默认填充，Consumer 的服务名称  到Attachments 中，dubbo 会在本次RPC 调用中传递给Rprovider
        if(isNotEmpty(APP_NAME)){
            invocation.getAttachments().put(CONSUMER_APP_NAME,APP_NAME);
        }

        try{
            RpcContext.removeServerContext();
            return invoker.invoke(invocation);
        }finally {
            RpcContext.removeContext();
        }
    }

    static class ConsumerContextListener implements Listener{

        @Override
        public void onResponse(Result appResponse, Invoker<?> invoker, Invocation invocation) {
            RpcContext.getServerContext().setAttachments(appResponse.getAttachments());
        }

        @Override
        public void onError(Throwable t, Invoker<?> invoker, Invocation invocation) {

        }
    }


}
