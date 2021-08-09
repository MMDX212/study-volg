package baobao.zerenlianmoshi.dubbo;

import baobao.Constant;
import org.apache.dubbo.rpc.*;

public class DubboProviderFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        try{
            String appName = RpcContext.getContext().getAttachment(Constant.CONSUMER_APP_NAME);

            // 这里我们就获取到了Consumer  中填充的服务名称

            //根据appName 我们就可以处理这个服务名称是不是我们想要的，否则就直接抛出异常

        }catch (Throwable throwable){
            //记录下获取异常，方便后需分析
        }
        return invoker.invoke(invocation);

    }

    //这里就基本实现怎么处理每一次RPC调用的拦截了，然后想要那个服务拦截，在provider里面的filter里面指定一下这个DubboProviderFilter就可以了，也可以全局都实现。
    //
    //注意 ：这个Filter 要是用DUBBO包里面的，不要搞错了。
}
