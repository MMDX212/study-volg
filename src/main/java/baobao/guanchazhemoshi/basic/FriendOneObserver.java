package baobao.guanchazhemoshi.basic;

public class FriendOneObserver implements Observer {
    @Override
    public void update(String message) {
        // 模拟处理业务逻辑
        System.out.println("FriendOne  知道了 你发动态了 " + message);
    }
}
