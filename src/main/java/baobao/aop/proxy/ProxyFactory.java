package baobao.aop.proxy;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyFactory {

    private  Object target; // 维护一个目标对象

    public  ProxyFactory(Object target){
        this.target = target;
    }

    // 为目标对象生成代理对象

    // 这样实现有啥好处呢？（JDK 动态代理）
    // 由于动态代理是程序运行后才生成的，哪个委托类需要被代理到，只要生成动态代理即可，避免了静态代理那样的硬编码，
    //另外所有委托类实现接口的方法都会在Proxy 的InvocationHandler.invoker() 中执行，这样如果要统计所有方法
    //执行时间这样相同的逻辑，可以统一在InvacationHandler 里重写，也就避免了静态代理那样需要在所有方法中插入同样代码的问题，
    //代码的可维护性就极大的提高了

    /**
     * 上面说的那么厉害，为啥Spring AOP 不用它呢？
     * JDK 动态代理虽好，但也有弱点，我们注意到newProxyInstance 的方法签名
     * public static Object newProxyInstance(ClassLoader loader,
     *                                          Class<?>[] interfaces,
     *                                          InvocationHandler h);
     *
     * 注意第二个参数Interfaces 是委托类的接口，是必传的，JDK 动态代理是通过与委托类实现同样的接口，
     * 然后在实现的接口方法里进行增强来实现的，这就意味着如果要用JDK 代理，委托类必须实现接口，这样的方式看起来有点蠢，更好的方式是什么呢？
     * 直接继承自委托类不就行了，这样委托类的逻辑不需要做任何改动，CGLib 就是这么做的
     */
    public  Object getProxyInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),
                new InvocationHandler(){
                    @Override
                    public  Object invoke(Object proxy, Method method,Object[] args) throws  Throwable{
                        System.out.println("计算开始时间");
                        //执行目标对象方法
                        method.invoke(target,args);
                        System.out.println("计算结束时间");
                        return  null;
                    }
                });
    }

    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();
        System.out.println(realSubject.getClass());
        Subject subject = (Subject) new ProxyFactory(realSubject).getProxyInstance();
        System.out.println(subject.getClass());
        subject.request();
    }
}
