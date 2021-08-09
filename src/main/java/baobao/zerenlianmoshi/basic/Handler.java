package baobao.zerenlianmoshi.basic;


/**
 * 首先定义一个抽象Handler 处理器，同时添加一个抽象处理方法handlerRequest,
 * 后面我们只需要编写具体的处理器来继承Handler类
 */
public abstract class Handler {

    protected  Handler handler;

    public void setHandler(Handler handler){
        this.handler = handler;
    }

    public abstract  void handlerRequest(Integer times);
}

