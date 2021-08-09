package baobao.celuemoshi.shizhan.core;

import baobao.celuemoshi.shizhan.global.CashSuper;

/**
 * 返利收费子类
 * 　　返利活动，输入返利条件和返利值，比如满300返100，moneyCoditation为300，moneyReturn为100。
 */
public class CashReturn extends CashSuper {

    private  double moneyCondition=0.0;
    private  double moneyReturn =0.0d;

    public  CashReturn(double moneyCondition,double moneyReturn){
        this.moneyCondition = moneyCondition;
        this.moneyReturn = moneyReturn;
    }

    @Override
    public double acceptCash(double money) {
        double result = money;
        if(money >= moneyCondition){
            result = money= Math.floor(money/moneyCondition) *moneyReturn;
        }
        return result;
    }
}
