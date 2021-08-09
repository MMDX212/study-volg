package baobao.zhuangpeimoshi.test;

import baobao.zhuangpeimoshi.appearance.BeautifulAppearance;
import baobao.zhuangpeimoshi.core.Phone;
import baobao.zhuangpeimoshi.interfaces.IAppearance;
import baobao.zhuangpeimoshi.interfaces.IPrice;
import baobao.zhuangpeimoshi.interfaces.IScreen;
import baobao.zhuangpeimoshi.interfaces.ISpeed;
import baobao.zhuangpeimoshi.price.MediumPrice;
import baobao.zhuangpeimoshi.screen.TouchScreen;
import baobao.zhuangpeimoshi.speed.QuickSpeed;

public class Test {

    public static void main(String[] args) {
        Phone huaWei = new Phone("华为");
        huaWei.setAppearance(new BeautifulAppearance());
        huaWei.setPrice(new MediumPrice());
        huaWei.setSpeed(new QuickSpeed());
        huaWei.setScreen(new IScreen() {
            @Override
            public String screen() {
                return "可触屏";
            }
        });
        System.out.println(huaWei);

        Phone NOKIA = new Phone();
        NOKIA.setName("诺基亚");
        NOKIA.setAppearance(new IAppearance() {
            @Override
            public String appearance() {
                return "外壳坚硬";
            }
        });
        NOKIA.setPrice(new IPrice() {
            @Override
            public String price() {
                return "价格低廉";
            }
        });
        NOKIA.setSpeed(new ISpeed() {
            @Override
            public String speed() {
                return "速度飞快";
            }
        });
        NOKIA.setScreen(new TouchScreen());
        System.out.println(NOKIA);

    }
}
