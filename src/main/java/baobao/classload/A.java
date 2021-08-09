package baobao.classload;

public class A {
    public void sayHi(){
        System.out.println("this is A,  我是被谁加载的: " + this.getClass().getClassLoader());
    }
}
