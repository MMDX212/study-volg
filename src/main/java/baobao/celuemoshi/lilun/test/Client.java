package baobao.celuemoshi.lilun.test;

import baobao.celuemoshi.lilun.global.Context;
import baobao.celuemoshi.lilun.role.ConcreteStrategyA;
import baobao.celuemoshi.lilun.role.ConcreteStrategyB;
import baobao.celuemoshi.lilun.role.ConcreteStrategyC;

public class Client {

    public static void main(String[] args) {
        Context context;

        context = new Context(new ConcreteStrategyA());
        context.contextInterface();

        context = new Context(new ConcreteStrategyB());
        context.contextInterface();

        context = new Context(new ConcreteStrategyC());
        context.contextInterface();
    }
}
