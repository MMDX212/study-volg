package baobao.aop.proxy;

import java.lang.reflect.InvocationHandler;

public class Proxy implements Subject {

    public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) {
        //loader: 代理类的ClassLoader，最终读取动态生成的字节码，并转成 java.lang.Class 类的一个实例（即类），通过此实例的 newInstance() 方法就可以创建出代理的对象
        //interfaces: 委托类实现的接口，JDK 动态代理要实现所有的委托类的接口
        //InvocationHandler: 委托对象所有接口方法调用都会转发到 InvocationHandler.invoke()，在 invoke() 方法里我们可以加入任何需要增强的逻辑 主要是根据委托类的接口等通过反射生成的
        return null;
    }

    /***
     * 静态代理主要有两大劣势
     *
     *  1.代理类只代理一个委托类（其实可以代理多个，但不符合单一职责原则），也就意味着如果要代理多个委托类，
     *  就要写多个代理（别忘了静态代理在编译前必须确定）
     *
     *  2.第一点还不是致命的，再考虑这样一种场景：如果每个委托类的每个方法都要被织入同样的逻辑，比如我要计算
     *  前文提到的每个委托类每个方法的耗时，就要在方法开始前，开始后分别织入计算时间的代码，那就算用代理类，它的方法也有无数这种重复的计算时间的代码
     *
     *
     *  该怎么改进呢？
     *
     *
     *  这就要提到动态代理了，
     *  静态代理的这些劣势主要是因为在编译前这些代理类是确定的，如果这些代理类是动态生成的呢，是不是可以省略一大堆的代码
     *
     *  动态代码 分为  JDK 动态代理和 Spring AOP 用到的CGLib 生成的代理
     */
    private RealSubject realSubject;

    public  Proxy(RealSubject subject){
        this.realSubject =subject;
    }


    @Override
    public void request() {
        // 执行代理逻辑
        System.out.println("卖房前");

        // 执行目标对象
        realSubject.request();

        //执行代理逻辑
        System.out.println("卖房后");
    }

    public static void main(String[] args) {
        // 被代理对象
        RealSubject subject = new RealSubject();

        //代理
        Proxy proxy = new Proxy(subject);

        // 代理请求
        proxy.request();
    }
}
