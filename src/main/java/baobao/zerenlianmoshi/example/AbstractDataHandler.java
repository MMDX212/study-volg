package baobao.zerenlianmoshi.example;


/**
 * 现实业务改造举例
 * 框架中既然都有这种思想，那么怎么运用到业务代码中呢？
 *
 * 还是给大家举一个例子：
 *
 * 商品详情展示我们可以是分模块展示的，比如头图，商品信息，sku信息，配送地址，分期付费等等。
 * @param <T>
 */
public abstract  class AbstractDataHandler<T> {

    //处理模块化数据
    protected abstract T doRequest(String query) throws Exception;

}
