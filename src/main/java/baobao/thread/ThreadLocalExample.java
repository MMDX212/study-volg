package baobao.thread;

import java.text.SimpleDateFormat;
import java.util.Random;

public class ThreadLocalExample implements Runnable {
    // SimpleDateFormat 不是线程安全的，所以每个线程都要有自己独立的副本
    private static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(()->new SimpleDateFormat("yyyyMMdd HHmm"));


    public static void main(String[] args) throws InterruptedException {
        ThreadLocalExample obj = new ThreadLocalExample();
        for (int i = 0; i <10 ; i++) {
            Thread t = new Thread(obj,""+i);
            Thread.sleep(new Random().nextInt(1000));
            t.start();
        }

        /**
         * "C:\Program Files\Java\jdk1.8.0_101\bin\java.exe" "-javaagent:F:\idea\IntelliJ IDEA 2019.2.2\lib\idea_rt.jar=61448:F:\idea\IntelliJ IDEA 2019.2.2\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_101\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_101\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_101\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_101\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_101\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_101\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_101\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_101\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_101\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_101\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_101\jre\lib\rt.jar;F:\python\soft;E:\work\dandano_o\target\classes;E:\maven\apache-maven-3.3.9\repo\org\springframework\boot\spring-boot-starter\2.4.0\spring-boot-starter-2.4.0.jar;E:\maven\apache-maven-3.3.9\repo\org\springframework\boot\spring-boot\2.4.0\spring-boot-2.4.0.jar;E:\maven\apache-maven-3.3.9\repo\org\springframework\boot\spring-boot-autoconfigure\2.4.0\spring-boot-autoconfigure-2.4.0.jar;E:\maven\apache-maven-3.3.9\repo\org\springframework\boot\spring-boot-starter-logging\2.4.0\spring-boot-starter-logging-2.4.0.jar;E:\maven\apache-maven-3.3.9\repo\ch\qos\logback\logback-classic\1.2.3\logback-classic-1.2.3.jar;E:\maven\apache-maven-3.3.9\repo\ch\qos\logback\logback-core\1.2.3\logback-core-1.2.3.jar;E:\maven\apache-maven-3.3.9\repo\org\apache\logging\log4j\log4j-to-slf4j\2.13.3\log4j-to-slf4j-2.13.3.jar;E:\maven\apache-maven-3.3.9\repo\org\apache\logging\log4j\log4j-api\2.13.3\log4j-api-2.13.3.jar;E:\maven\apache-maven-3.3.9\repo\org\slf4j\jul-to-slf4j\1.7.30\jul-to-slf4j-1.7.30.jar;E:\maven\apache-maven-3.3.9\repo\jakarta\annotation\jakarta.annotation-api\1.3.5\jakarta.annotation-api-1.3.5.jar;E:\maven\apache-maven-3.3.9\repo\org\springframework\spring-core\5.3.1\spring-core-5.3.1.jar;E:\maven\apache-maven-3.3.9\repo\org\springframework\spring-jcl\5.3.1\spring-jcl-5.3.1.jar;E:\maven\apache-maven-3.3.9\repo\org\yaml\snakeyaml\1.27\snakeyaml-1.27.jar;E:\maven\apache-maven-3.3.9\repo\org\springframework\boot\spring-boot-devtools\2.4.0\spring-boot-devtools-2.4.0.jar;E:\maven\apache-maven-3.3.9\repo\org\projectlombok\lombok\1.18.16\lombok-1.18.16.jar;E:\maven\apache-maven-3.3.9\repo\io\netty\netty-all\4.1.25.Final\netty-all-4.1.25.Final.jar;E:\maven\apache-maven-3.3.9\repo\org\apache\dubbo\dubbo\3.0.0\dubbo-3.0.0.jar;E:\maven\apache-maven-3.3.9\repo\org\springframework\spring-context\5.3.1\spring-context-5.3.1.jar;E:\maven\apache-maven-3.3.9\repo\org\springframework\spring-aop\5.3.1\spring-aop-5.3.1.jar;E:\maven\apache-maven-3.3.9\repo\org\springframework\spring-beans\5.3.1\spring-beans-5.3.1.jar;E:\maven\apache-maven-3.3.9\repo\org\springframework\spring-expression\5.3.1\spring-expression-5.3.1.jar;E:\maven\apache-maven-3.3.9\repo\com\alibaba\spring\spring-context-support\1.0.8\spring-context-support-1.0.8.jar;E:\maven\apache-maven-3.3.9\repo\org\javassist\javassist\3.23.1-GA\javassist-3.23.1-GA.jar;E:\maven\apache-maven-3.3.9\repo\org\eclipse\collections\eclipse-collections\10.4.0\eclipse-collections-10.4.0.jar;E:\maven\apache-maven-3.3.9\repo\org\eclipse\collections\eclipse-collections-api\10.4.0\eclipse-collections-api-10.4.0.jar;E:\maven\apache-maven-3.3.9\repo\com\google\code\gson\gson\2.8.6\gson-2.8.6.jar;E:\maven\apache-maven-3.3.9\repo\com\alibaba\fastjson\1.2.70\fastjson-1.2.70.jar;E:\maven\apache-maven-3.3.9\repo\org\roaringbitmap\RoaringBitmap\0.9.0\RoaringBitmap-0.9.0.jar;E:\maven\apache-maven-3.3.9\repo\org\roaringbitmap\shims\0.9.0\shims-0.9.0.jar;E:\maven\apache-maven-3.3.9\repo\com\101tec\zkclient\0.10\zkclient-0.10.jar;E:\maven\apache-maven-3.3.9\repo\org\slf4j\slf4j-api\1.7.30\slf4j-api-1.7.30.jar;E:\maven\apache-maven-3.3.9\repo\org\apache\zookeeper\zookeeper\3.4.8\zookeeper-3.4.8.jar;E:\maven\apache-maven-3.3.9\repo\log4j\log4j\1.2.16\log4j-1.2.16.jar;E:\maven\apache-maven-3.3.9\repo\jline\jline\0.9.94\jline-0.9.94.jar;E:\maven\apache-maven-3.3.9\repo\io\netty\netty\3.7.0.Final\netty-3.7.0.Final.jar" baobao.thread.ThreadLocalExample
         * Thread Name= 0default Formatter = yyyyMMdd HHmm
         * Thread Name= 0 formatter = yy-M-d ah:mm
         * Thread Name= 1default Formatter = yyyyMMdd HHmm
         * Thread Name= 2default Formatter = yyyyMMdd HHmm
         * Thread Name= 3default Formatter = yyyyMMdd HHmm
         * Thread Name= 4default Formatter = yyyyMMdd HHmm
         * Thread Name= 1 formatter = yy-M-d ah:mm
         * Thread Name= 4 formatter = yy-M-d ah:mm
         * Thread Name= 3 formatter = yy-M-d ah:mm
         * Thread Name= 5default Formatter = yyyyMMdd HHmm
         * Thread Name= 2 formatter = yy-M-d ah:mm
         * Thread Name= 5 formatter = yy-M-d ah:mm
         * Thread Name= 6default Formatter = yyyyMMdd HHmm
         * Thread Name= 6 formatter = yy-M-d ah:mm
         * Thread Name= 7default Formatter = yyyyMMdd HHmm
         * Thread Name= 7 formatter = yy-M-d ah:mm
         * Thread Name= 8default Formatter = yyyyMMdd HHmm
         * Thread Name= 9default Formatter = yyyyMMdd HHmm
         * Thread Name= 9 formatter = yy-M-d ah:mm
         * Thread Name= 8 formatter = yy-M-d ah:mm
         *
         * 从输出中可以看出，Thread-0 已经改变了formatter 的值，但仍然是thread-2 默认格式化程序与初始化值相同，其他线程也一样
         *
         * 上面有一段代码用到了创建ThreadLocal 变量的那段代码用到了JAVA8 的知识，它等于下面这段代码，如果你写了下面这段代码的话，IDEA h
         * 会提示你转换为java8 的格式。因为ThreadLocal 类在java8 中扩展，使用了一个新的方法withInitial(),将Supplier 功能接口作为参数。
         *
         *private static final ThreadLocal<SimpleDateFormat> format = new ThreadLocal<SimpleDateFormat>(){
         * @Override
         * protected SimpleDateFormat initialValue(){
         *     return new SimpleDateFormat("yyyyMMdd HHmm");
         * }
         *}
         *ThreadLocal 内存泄漏问题
         * threadlocal 中 使用的key 为threadlocal 的弱引用，而value 是强引用。所以，如果threadlocal 没有被外部强引用的情况下，在垃圾回收的时候，key 会被清理掉。这样一来
         * threadlocal 中就会出现key 为null 的entry ,假如我们不做任何措施的话，value 永远无法被gc 回收，这个时候就可能产生内存泄漏。
         * threadLocalMap 实现中已经考虑了这种情况，在调用set(),get(),remove()方法的时候，会清理掉key 为null的记录。使用完
         * threadlocal 方法后，最好手动调用remove() 方法
         *
         *staticclass Entry extends WeakReference<ThreadLocal<?>> {
         *              //The value associated with this ThreadLocal.
         *Object value;
         *
         *Entry(ThreadLocal < ? > k, Object v){
         *super(k);
         *value = v;
         *}
         *}
         */
    }

    @Override
    public void run() {
        System.out.println("Thread Name= " + Thread.currentThread().getName()+ "default Formatter = " +formatter.get().toPattern());

        try{
            Thread.sleep(new Random().nextInt(1000));
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        formatter.set(new SimpleDateFormat());

        System.out.println("Thread Name= "+Thread.currentThread().getName()+" formatter = "+formatter.get().toPattern());
    }
}
