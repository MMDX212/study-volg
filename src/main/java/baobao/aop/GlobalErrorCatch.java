package baobao.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface GlobalErrorCatch {
    // 首先定义如上注解，然后将service 中方法里[try...catch...]
    //移除掉，在方法签名上加上上述我们定义好的注解
}
