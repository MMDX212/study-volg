package baobao.celuemoshi.lilun.global;

import baobao.celuemoshi.lilun.role.Strategy;

public class Context {
    Strategy strategy;

    public  Context(Strategy strategy){
        this.strategy = strategy;
    }

    public  void contextInterface(){
        strategy.algorithmInterface();
    }
}
