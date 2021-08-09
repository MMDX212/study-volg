package baobao.aop.proxy;

public class RealSubject implements Subject {
    @Override
    public void request() {
        // 卖房
        System.out.println("卖房");
    }
}
