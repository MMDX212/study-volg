package baobao.aop.proxy;

import org.springframework.cglib.proxy.Enhancer;

public class CGlibProxy {

    public static void main(String[] args) {
        // 创建Enhancer 对象，类似于JDK 动态代理的Proxy 类，下一步就是设置几个参数
        Enhancer enhancer = new Enhancer();
        // 设置目标类的字节码文件
        enhancer.setSuperclass(RealSubject.class);
        //设置回调函数
        enhancer.setCallback(new MyMethodInterceptor());
        
        //这里的creat 方法就是正式创建代理类
        RealSubject proxyDog = (RealSubject) enhancer.create();
        // 调用代理类是eat  方法
        proxyDog.request();
    }

    /**
     * 打印如下
     *
     * 代理类:class com.example.demo.proxy.staticproxy.RealSubject$$EnhancerByCGLIB$$889898c5
     * 目标类增强前！！！
     * 卖房
     * 目标类增强后！！！
     *
     *
     * 可以看到主要就是利用 Enhancer 这个类来设置委托类与方法拦截器，这样委托类的所有非 final 方法就能被方法拦截器拦截，
     * 从而在拦截器里实现增强
     *
     *底层实现原理是啥
     *之前也说了它是通过继承自委托类，重写委托类的非 final 方法（final 方法不能重载），并在方法里调用委托类的方法来实现代码增强的，它的实现大概是这样
     *
     *
     * public class RealSubject {
     *    @Override
     *    public void request() {
     *        // 卖房
     *        System.out.println("卖房");
     *    }
     * }
     *
     * ** 生成的动态代理类（简化版）*
     *
     * public class RealSubject$$EnhancerByCGLIB$$889898c5 extends RealSubject {
     *
        @Override
     *
        public void request() {
     *System.out.println("增强前");
     *super.request();
     *System.out.println("增强后");
     *}
     *
    }

    *可以看到它并不要求委托类实现任何接口，而且 CGLIB 是高效的代码生成包，底层依靠 ASM（开源的 java 字节码编辑类库）操作字节码实现的，性能比 JDK 强，
     * 所以 Spring AOP 最终使用了 CGlib 来生成动态代理
     *
     * CGlib 动态代理使用上有啥限制吗
     *
     * 第一点之前已经已经说了，只能代理委托类中任意的非 final 的方法，另外它是通过继承自委托类来生成代理的，所以如果委托类是 final 的，就无法被代理了（final 类不能被继承）
     *
     *
     * JDK 动态代理的拦截对象是通过反射的机制来调用被拦截方法的，CGlib 呢，它通过什么机制来提升了方法的调用效率。
     *
     * 由于反射的效率比较低，所以 CGlib 采用了FastClass 的机制来实现对被拦截方法的调用。FastClass 机制就是对一个类的方法建立索引，通过索引来直接调用相应的方法、
     *
     * https://www.cnblogs.com/cruze/p/3865180.html
     *
     * https://cloud.tencent.com/developer/article/1584491
     */
}
