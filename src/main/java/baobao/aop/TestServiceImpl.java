package baobao.aop;

public class TestServiceImpl implements TestService {

//    @Override
//    @GlobalErrorCatch
//    public ServiceResultTO<Boolean> test(){
//        // 此处写服务里的执行逻辑
//        boolean result = xxx;
//        return ServiceResultTO.buildSuccess(result);
//    }

    @Override
    public void eatCarrot() {
        System.out.println("吃萝卜");
    }

    @Override
    public void eatMushroom() {
        System.out.println("吃蘑菇");
    }

    @Override
    public void eatCabbage() {
        System.out.println("吃白菜");
    }
    /**
     * 假设有以上TestService,实现了吃萝卜，吃蘑菇，吃白菜三个方法，这三个方法都用切面织入，所以他们都是joinpoints,
     * 但现在我只想对吃萝卜这个joinpoints 前后织入advice,该怎么办呢，首先当然要声明pointcut
     * 表达式，这个表达式表明只想织入吃萝卜这个joinpoints，指明了之后再让advice应用于此pointcut
     * 不就完了，比如我想在吃萝卜前洗手，吃萝卜后买单，可以写出如下切面逻辑
     */
}
