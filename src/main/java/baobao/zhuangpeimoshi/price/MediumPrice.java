package baobao.zhuangpeimoshi.price;

import baobao.zhuangpeimoshi.interfaces.IPrice;

public class MediumPrice  implements IPrice {
    @Override
    public String price() {
        return "价格中等";
    }
}
