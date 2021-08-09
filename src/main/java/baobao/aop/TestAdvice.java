package baobao.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAdvice {
    //1.定义所有带有GlobalErrorCatch 的注解的方法为PointCut
    @Pointcut("@annotation(baobao.aop.GlobalErrorCatch)")
    private  void globalCatch(){}
    // 2.将around advice 作用于globalCatch(){} 此 PointCut
   @Around("globalCatch()")
   public Object handlerGlobalResult(ProceedingJoinPoint point) throws  Throwable{
        try{
            return point.proceed();
        }catch (Exception e){
            System.out.println("执行错误" + e);
          //  return ServiceResultTO.buildFailed("系统错误");
            return null;
        }
   }





    //1. 定义Pointcut
    @Pointcut("execution(* baobao.aop.TestServiceImpl.eatCarrot())")
    private void eatCarrot(){}

    //2.定义应用于JoinPoint 中所有满足PointCut 条件的advice ，这里我们使用around advice，在其中织入增强逻辑
    @Around("eatCarrot()")
    public void handlerRpcResult(ProceedingJoinPoint point) throws  Throwable{
        System.out.println("吃萝卜前先洗手");
        // 原来的TestServiceImpl.eatCarrot 逻辑，可视情况决定是否执行
        point.proceed();
        System.out.println("吃萝卜后买单");
    }
}
