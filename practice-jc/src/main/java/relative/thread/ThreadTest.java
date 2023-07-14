package relative.thread;

import org.junit.Test;

import java.io.IOException;

/**
 * 多线程通过final访问主线程局部变量
 * https://xtuhcy.iteye.com/blog/2170295
 *
 * 有关线程安全的探讨--final、static、单例、线程安全
 * https://www.cnblogs.com/bellkosmos/p/5340711.html
 *
 * @author chensy
 * @date 2019-05-29 14:36
 */
public class ThreadTest {

    /**
     * 线程_概述
     * 1）A thread goes through various stages in its life cycle. For example, a thread is born, started, runs, and then dies.
     *  （线程的生命周期中包含多个状态，例如：born、started、runs、dies等等）
     *
     * 2）Every Java thread has a priority that helps the operating system determine the order in which threads are scheduled.
     *  （每个线程都有优先级，帮组操作系统决定执行那个线程）
     *
     * 3）However, thread priorities cannot guarantee the order in which threads execute and are very much platform dependent.
     *  （但是优先级，并不能保证就是线程的执行顺序，执行顺序更多依赖操作系统）
     *
     * 4）线程的创建，主要有两种方式：
     *   a）Create a Thread by Implementing a Runnable Interface （通过实现Runnable接口创建线程）
     *   b）Create a Thread by Extending a Thread Class（通过继承Thread类创建线程）
     *
     * 5）Thread中的主要方法：
     *
     * 参考链接：
     * a）https://docs.oracle.com/javase/tutorial/essential/concurrency/runthread.html Oracle官网关于Thread的介绍
     * b）https://www.tutorialspoint.com/java/java_multithreading.htm java线程介绍
     */

    /**
     * 场景1：线程的创建
     */

    // 单线程使用
    @Test
    public void test_single_thread() {
        SafeVar safeVar = new SafeVar();
        Thread thread = new Thread(safeVar);
        thread.start();
    }

    /**
     * 场景2：多线程处理
     */
    @Test
    public void test_multi_thread() {
       SafeVar safeVar1 = new SafeVar();
       SafeVar safeVar2 = new SafeVar();

       // Thread的run方法是启动线程后，线程获取到资源后，回调run方法的，不用主动去掉，主动去掉用的，只是一个普通方法，并没有发挥线程作用
       // safeVar1.run();
       // safeVar2.run();

       // 多线程时，对于共享变量，有线程安全问题
       Thread thread1 = new Thread(safeVar1);
       Thread thread2 = new Thread(safeVar2);
       thread1.start();
       thread2.start();

       System.out.println("共享变量值：" + SafeVar.var); //此处输出的共享变量值，可能有多种，因为该语句是在主线程中，其它线程还在运算中
    }

    /**
     * 场景3：join方法使用（线程加入）
     *
     * 理解：就是调用了哪个线程的join方法，就先执行完哪个线程，再执行当前线程，当前线程先进入阻塞状态
     */
    @Test
    public void test_join() throws IOException, InterruptedException {
        long current = System.currentTimeMillis();
        System.out.println("当前线程开始");

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("执行子线程");
            }
        };
        Thread t1 = new Thread(runnable);
        t1.start();

        t1.join(); // 线程加入

        System.out.println("当前线程结束，耗时 = " + (System.currentTimeMillis() - current) / 1000);
        System.in.read();

        /**
         * 输出结果：
         * 当前线程开始
         * 执行子线程
         * 当前线程结束，耗时 = 5
         *
         * 结果分析：
         * 当前线程执行了t1.join()，就会加入t1子线程。当前线程进入阻塞状态，直到t1执行完成，才进入就绪状态
         */
    }

    /**
     * 场景4：yield方法使用（线程让步）
     * yield：让步
     *
     * yield()的功能用途：调用yield方法会让当前线程休眠，并让出CPU执行的时间片，与其它线程一起重新竞争时间片
     * （当前线程可能抢到时间片执行，也可能没有抢到时间片执行）
     */
    @Test
    public void test_yield() {
        YieldTest yt1 = new YieldTest("张三");
        YieldTest yt2 = new YieldTest("李四");
        yt1.start();
        yt2.start();

        /**
         * 结果输出（每次运行都有不能的结果输出，哪个线程抢到时间片，就对应执行哪个线程）
         *
         * 结果一：
         * ...李四----30...
         * ...张三----14...
         * ...张三----15...
         *
         * 结果二：
         * ...张三----30...
         * ...李四----1...
         * ...李四----2...
         */
    }

    public class YieldTest extends Thread {
        public YieldTest(String name) {
            super(name);
        }

        @Override
        public void run() {
            for (int i = 1; i <= 50; i++) {
                System.out.println("" + this.getName() + "-----" + i);
                // 当i为30时，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）
                if (i == 30) {
                    this.yield();
                }
            }
        }
    }
}
