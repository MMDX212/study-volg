package baobao.celuemoshi111;
// 省略 MultiItemShare以及SingleItemShare策略

import java.util.HashMap;
import java.util.Map;

// 分享工厂
public class ShareFactory {

    // 定义策略枚举
    enum ShareType {
        SINGLE("single", "单商品"),
        MULTI("multi", "多商品"),
        ORDER("order", "下单");
        // 场景对应的编码
        private String code;

        // 业务场景描述
        private String desc;

        ShareType(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }
        // 省略 get set 方
    }


    // 定义策略map缓存
    private static final Map<String, ShareStrategy> shareStrategies = new HashMap<>();
    static {
        shareStrategies.put("order", new OrderItemShare());
        shareStrategies.put("single", new SingleItemShare());
        shareStrategies.put("multi", new MultiItemShare());
    }
    // 获取指定策略
    public static ShareStrategy getShareStrategy(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("type should not be empty.");
        }
        return shareStrategies.get(type);
    }

    public static void main(String[] args) {
        // 测试demo
        String shareType = "order";
        ShareStrategy shareStrategy = ShareFactory.getShareStrategy(shareType);
        shareStrategy.shareAlgorithm("order");
        // 输出结果：当前分享图片是order

        /**
         * 这里策略模式就已经改造完了。在client请求端，根本看不到那么多的if else判断，只需要传入对应的策略方式即可，这里我们维护了一个策略缓存map，在直接调用的ShareFactory获取策略的时候就直接是从换种获取策略类对象。
         *
         * 这就已经达到了行为解偶的思想。同时也避免了长串的if else 判断。
         */

        /**
         * 优点：
         * 算法策略可以自由实现切换
         * 扩展性好，加一个策略，只需要增加一个类
         */


        /**
         * 缺点：
         * 策略类数量多
         * 需要维护一个策略枚举，让别人知道你当前具有哪些策略
         */
    }
}
