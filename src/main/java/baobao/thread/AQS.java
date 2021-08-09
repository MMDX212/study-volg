package baobao.thread;

public class AQS {
    /**
     * AQS 是一个用来构建锁和同步器的框架，使用AQS 能简单且高效地构造出应用广泛的大量同步器，比如我们提到的Reentrantlock,Samaphore,
     * 其他的诸如ReentrantReadWriteLock,SynchronousQueue,FutureTask 等等皆是基于AQS的。
     *
     * AQS 原理分析
     *
     *
     * 在面试中被问到并发知识的时候，大多都会被问到“请你说一下自己对于AQS原理的理解”。
     * 下面给大家一个示例供大家参加，面试不是背题，
     * 大家一定要加入自己的思想，即使加入不了自己的思想也要保证自己能够通俗的讲出来而不是背出来。
     *
     *
     *AQS  核心思想是，如果被请求的共享资源空闲，则将当前请求资源的线程设置为有效的工作线程，并且将共享资源
     * 设置为锁定状态。如果被请求的共享资源被占用，那么就需要一套线程阻塞等待以及被唤醒时锁分配的机制，
     * 这个机制AQS 是用CLH 队列锁实现的，即将暂时获取不到锁的线程加入到队列中。
     *
     * CLH（Craig,Landin,and Hagersten） 队列是一个虚拟的双向队列（虚拟的双向队列即不存在队列实例，仅存在
     * 结点之间的关联关系。AQS是将每条请求共享资源的线程封装成一个CLH锁队列的一个节点（Node）来实现锁的分配）
     *
     * AQS 使用一个int 成员变量来表示同步状态，通过内置的FIFO 队列来完成获取资源线程的排队工作。AQS 使用CAS 对该同步状态进行
     * 原子操作实现对其值的修改
     *
     * private volatile int state; // 共享变量，使用volatile 修饰保证可见性
     *
     * 状态信息通过protected 类型的getState,setState,compareAndSetState 进行操作
     *
     * // 返回同步状态的当前值
     *
     * protected  final int getState(){
     *     return state;
     * }
     *
     * //设置同步状态的值
     * protected final void setState(int newState){
     *     state = newState;
     * }
     * //原子地（CAS操作） 将同步状态值设置为给定值update 如果当前同步状态的值等于expect(期望值)
     * protected final boolean compareAndSetState(int expect,int update){
     *     return unsafe.compareAndSwapInt(this,stateOffset,expect,update);
     * }
     *
     *
     * AQS 对资源的共享方式
     *
     *      AQS 定义两种资源共享方式
     *          Exclusive(独占): 只有一个线程能执行，如ReentrantLock.又可分为公平锁和非公平锁；
     *          公平锁：按照线程在队列中的排队顺序，先到者先拿到锁
     *          非公平锁：当线程要获取锁时，无视队列顺序直接去抢锁，谁抢到就是谁的
     *
     *          Share(共享):多个线程可同时执行，如Semaphore/CountDownLatch.
     *
     *
     *          6.3. AQS 组件总结
     * Semaphore(信号量)-允许多个线程同时访问： synchronized 和 ReentrantLock 都是一次只允许一个线程访问某个资源，Semaphore(信号量)可以指定多个线程同时访问某个资源。
     * CountDownLatch （倒计时器）： CountDownLatch是一个同步工具类，用来协调多个线程之间的同步。这个工具通常用来控制线程等待，它可以让某一个线程等待直到倒计时结束，再开始执行。
     * CyclicBarrier(循环栅栏)： CyclicBarrier 和 CountDownLatch 非常类似，它也可以实现线程间的技术等待，但是它的功能比 CountDownLatch 更加复杂和强大。主要应用场景和 CountDownLatch 类似。CyclicBarrier 的字面意思是可循环使用（Cyclic）的屏障（Barrier）。它要做的事情是，让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活。
     * CyclicBarrier默认的构造方法是 CyclicBarrier(int parties)，其参数表示屏障拦截的线程数量，每个线程调用await()方法告诉 CyclicBarrier 我已经到达了屏障，然后当前线程被阻塞。
     */
}
