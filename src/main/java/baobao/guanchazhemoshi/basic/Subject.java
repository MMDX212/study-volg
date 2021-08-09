package baobao.guanchazhemoshi.basic;



public interface Subject {

    // 添加订阅关系
    void attach(Observer observer);

    // 移除订阅关系
    void detach(Observer observer);

    // 通知订阅关系
    void notifyObservers(String message);
}
