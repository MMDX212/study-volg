package baobao.celuemoshi.shizhan.core;

import baobao.celuemoshi.shizhan.global.CashSuper;

/**
 * 打折收费子类
 * 　　打折活动，根据折扣返回打折后的价格。
 */
public class CashRebate extends CashSuper {

    private double moneyRebate =1;
    public   CashRebate(double moneyRebate){
        this.moneyRebate = moneyRebate;
    }


    @Override
    public double acceptCash(double money) {
        return money * moneyRebate;
    }
}
