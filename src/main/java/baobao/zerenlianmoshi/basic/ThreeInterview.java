package baobao.zerenlianmoshi.basic;

public class ThreeInterview extends Handler {
    @Override
    public void handlerRequest(Integer times) {
        // 条件判断是否属于当前Handler 的处理范围之内，不是则向下传递Handler处理器
        if(times == 3){
            //假设直立式处理的业务逻辑代码
            System.out.println("第三次面试"+ times +",恭喜面试通过，HR会跟你联系！！！");
        }
    }


    public static void main(String[] args) {
        Handler first = new FirstInterview();
        Handler second = new SecondInterview();
        Handler three = new ThreeInterview();

        first.setHandler(second);
        second.setHandler(three);

        //第一次面试

        first.handlerRequest(1);
        System.out.println();
        //第二次面试
        first.handlerRequest(2);
        System.out.println();
        //第三次面试
        first.handlerRequest(3);
        System.out.println();
    }
}
