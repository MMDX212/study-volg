package baobao.zhuangpeimoshi.core;

import lombok.Data;
import baobao.zhuangpeimoshi.interfaces.IAppearance;
import baobao.zhuangpeimoshi.interfaces.IPrice;
import baobao.zhuangpeimoshi.interfaces.IScreen;
import baobao.zhuangpeimoshi.interfaces.ISpeed;

@Data
public class Phone {
    private  String name;
    private IAppearance appearance;
    private IPrice price;
    private ISpeed speed;
    private IScreen screen;

    public Phone(){
        this("无名");
    }

    public  Phone(String name){
        this.name = name;
    }

    @Override
    public  String toString(){
        return  this.name +"是一款" + this.appearance.appearance() +","
                +this.price.price() +","+this.speed.speed()+","+this.screen.screen();
    }
}
