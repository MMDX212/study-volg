package baobao.aop.proxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * AOP 就是用的CGLib 的形式来生成的，JDK 动态代理使用了Proxy 来创建代理类，
 * 增强逻辑写在InvacationHandler.invoke()里，CGLib 动态代理也提供了类似的
 * Enhance 类，增强逻辑写在MethodInterceptor.intercept() 中，也就是说所有委托类的
 * 非 final 方法都会被方法拦截器拦截，在说它的原理之前先来看看它是怎么用的
 */
public class MyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("目标类增强前");
        // 注意这里的方法调用，不用反射
        Object object = methodProxy.invokeSuper(o, objects);
        System.out.println("目标类增强后");
        return object;
    }


}
