package baobao.zerenlianmoshi.example;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DataAggregation {
    @Autowired
    private SkuInfoHandler skuInfoHandler;
    @Autowired
    private ItemInfoHandler itemInfoHandler;

    public Map convertItemDetail(){
        Map result = new HashMap();
        result.put("skuInfoHandler",skuInfoHandler.doRequest("模拟数据请求"));
        result.put("itemInfoHandler",itemInfoHandler.doRequest("模拟数据请求"));
        return result;
    }


    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        DataAggregation dataAggregation = (DataAggregation) applicationContext.getBean("dataAggregation");
        Map map = dataAggregation.convertItemDetail();
        System.out.println(JSON.toJSONString(map));


        // 打印的结果数据
        // {"skuInfoHandler":{"skuId":78910,"skuName":"测试SKU"},"itemInfoHandler":{"itemId":123456,"itemName":"测试商品"}}


        /**
         * 这个例子其实是经过一点小小的改动的，我们没有通过向下传递处理器的方式，而是通过实际业务逻辑在 convertItemDetail 的方法中去构建每个模块的数据，最后返回出一个Map结构数据。
         *
         * 这里其实还有另外的一种写法，把每一个需要处理的Handler 可以加载到一个List容器中，然后循环调用每个Handler中的doRequest方法，当然这是针对一些其他的业务场景这么写。
         *
         * 看完大家也能发现其实每个Handler是可以共用的，每一块业务的代码逻辑非常的清晰，这样的代码写出来就感觉很舒服了。
         */
    }
}
