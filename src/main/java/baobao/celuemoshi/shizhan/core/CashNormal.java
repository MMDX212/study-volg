package baobao.celuemoshi.shizhan.core;

import baobao.celuemoshi.shizhan.global.CashSuper;

/**
 * 正常收费子类
 *
 * 没有任何活动的情况，正常收费，返回原价
 */
public class CashNormal extends CashSuper {
    @Override
    public double acceptCash(double money) {
        return money;
    }
}
