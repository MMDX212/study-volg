package baobao.celuemoshi111;

public class OrderItemShare implements ShareStrategy {
    @Override
    public void shareAlgorithm(String param) {
        System.out.println("当前 分享图片是 " + param);
    }
}
