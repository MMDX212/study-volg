package baobao.zerenlianmoshi.basic;

public class SecondInterview extends Handler {
    @Override
    public void handlerRequest(Integer times) {
        // 条件判断是否属于当前Handler 的处理范围之内，不是则向下传递Handler处理器
        if(times == 2){
            //假设直立式处理的业务逻辑代码
            System.out.println("第二次面试"+ times);
        }
        handler.handlerRequest(times);
    }
}
