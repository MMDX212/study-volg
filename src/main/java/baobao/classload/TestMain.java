package baobao.classload;

import java.net.URL;
import java.net.URLClassLoader;

public class TestMain {

    public static void main(String[] args) {
        ClassLoader systenLoader =TestMain.class.getClassLoader();
        System.out.println("系统类加载器: " + systenLoader);

        ClassLoader extLoader =systenLoader.getParent();
        System.out.println("系统类加载器的parent（扩展类加载器）:" +extLoader);

        ClassLoader bootClassLoader =extLoader.getParent();
        System.out.println("扩展类加载器的parent（启动类加载器）:" + bootClassLoader);

        /**
         * 1、启动类加载器（BootClassLoader）由C++语言编写，负责在JVM启动时加载jdk自身的一些核心class类
         * （jar包形式）到JVM中，加载时寻找资源的路径由只读系统属性：”sun.boot.class.path“ 指定，
         * 一般为：”JAVA_HOME/jre/classes“目录（在该目录下只能放class文件，jar包形式文件不生效）。
         */
        String cc = System.getProperty("sun.boot.class.path");
        System.out.println("启动类加载器加载路径" + cc);

        /**
         * 2,扩展类加载器（ExtClassLoader）,负责加载位于系统属性: "java.ext.dirs" 指向的目录下加载class 文件
         * （jar 包 或者 直接class 文件形式） 到JVM中 ，比如通常ext类加载路径为: "$JAVA_HOMEEx/jre/lib/ext"。
         *
         * 查看扩展类加载器的类加载路径 可以通过获取系统属性: " java.ext.dirs" 进行查看  或 向上转型为URLClassLoader(
         * 上面说 扩展类加载器继承自URLCLassLoader),查看位于父类URLClassLoader中的urls 属性 的方式进行查看
         *
         */

        String var = System.getProperty("java.ext.dirs");
        System.out.println("扩展类加载器加载路径: " + var);
        URLClassLoader extLoaderA = (URLClassLoader)  ClassLoader.getSystemClassLoader().getParent();
        URL[] urLs = extLoaderA.getURLs();

        /**
         * 3, 系统类加载器（AppClassLoader） ,负责加载应用classpath 路径下的class文件（jar包或者直接class文件形式）
         * 到JVM 中，当系统中没有设置classpath路径时，默认加载当前路径下的class文件
         *
         * 查看系统类加载器的类加载路径可以通过获取系统属性: "java.class.path" 进行查看或向上转型为URLClassLoader
         * 上面说扩展类加载器继承自URLClassLoader,查看位于父类URLClassLoader中urls 属性的方式进行查看
         *
         */

        String sysPath = System.getProperty("java.class.path");
        System.out.println("系统类加载器加载路径:" + sysPath);
        URLClassLoader extLoaderB = (URLClassLoader)  ClassLoader.getSystemClassLoader();
        URL[] urLsa = extLoaderB.getURLs();


        //   JVM 类加载双亲委托机制
        /**
         * JVM 加载 class 类文件 到 虚拟机时，默认首先采用系统类加载器去加载用到的class 类，采用的是双亲委托加载机制
         *
         * 所谓 双亲委托，就是当前类加载器（以系统类加载器为例） 在加载一个类时，委托给其双亲（
         * 注意 这里的双亲 指的是类加载器中parent  属性指向的类加载器） 先进行加载。
         *
         *
         * 双亲类加载器 在加载时同样委托给自己的双亲，如此反复，直到某个类加载器没有双亲为止（
         * 通常情况下值双亲为 null,也即为当前的双亲 为扩展类加载器，其parent  为 启动类加载器），
         * 然后开始在依次在各自的路径下寻找，加载class类
         *
         */


        System.out.println("this is TestMain , 我是被谁加载的: " + TestMain.class.getClassLoader());
        A a = new A();
        a.sayHi();


        /**
         * 双亲委托加载方向
         *
         * 类加载器在加载类时，只能向上递归委托其双亲进行类加载，而不可能从双亲再反向委派当前类加载器来进行类加载，
         *
         * 在中国象棋中，卒子过河之后的行走轨迹永远只能是前进或者左右平移，可以很形象的比作双亲委托类加载的这种方向性。
         *
         * 卒子过河比喻当前类加载器委派其双亲加载了某个类。这个类的后续依赖的加载已经和当前类加载器没有关系。
         * 过河之后的卒子只能前进，表示双亲在加载类的依赖类时，只能继续递归进行双亲委派。
         * 左右平移表示双亲在递归双亲委派加载失败后，在双亲类加载器自己的加载路径中进行加载。
         * 为了表明委派具有方向性，我们继续拿上面的TestMain.class和A.class两个类做实验。
         *
         *
         * 原因就是上述说的委派加载具有方向性导致的：
         *
         * 1、运行java命令执行TestMain程序时，系统类加载器准备加载TestMain，根据双亲委派机制，先委派给其双亲进行加载，最后，双亲扩展类加载器在其加载路径中的TestMain.jar中找到了TestMain.class，完成了TestMain的加载。
         *
         * 2、TestMain中依赖了A，此时，会根据加载了TestMain的类加载器：扩展类加载器去加载A，加载方式根据委托机制递归委托给双亲加载，
         * 扩展类加载器的双亲为启动类加载器，在启动类加载器的加载路径中不存在A，加载失败，
         * 此时由扩展类加载器在自己的加载路径中加载A，也因为加载路径中没有A.class存在，
         * A.class存在于系统类加载器的加载路径中，但是扩展类加载器不会再返回去委托系统类加载器进行加载，所以直接抛出加载失败异常，出现了上述的错误。
         */

    }
}
