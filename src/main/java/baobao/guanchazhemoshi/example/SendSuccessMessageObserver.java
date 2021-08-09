package baobao.guanchazhemoshi.example;

import baobao.guanchazhemoshi.basic.ConcreteSubject;
import baobao.guanchazhemoshi.basic.Observer;

public class SendSuccessMessageObserver implements Observer {
    @Override
    public void update(String message) {
        // 处理业务逻辑
        System.out.println("注册成功");
    }


    public static void main(String[] args) {
        ConcreteSubject subject = buildSubject();
        subject.notifyObservers("");
    }


    private static  ConcreteSubject buildSubject(){
        ConcreteSubject subject = new ConcreteSubject();
        subject.attach(new SendSuccessMessageObserver());
        subject.attach(new SendNewPersonCouponObserver());
        return subject;
    }
}
