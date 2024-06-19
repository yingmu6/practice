package relative.thread;

import org.junit.Test;

import java.io.IOException;

/**
 * @author chensy
 * @date 2019-05-29 14:36
 */
public class ThreadTest { //@JcY-Doing

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
     * c）https://xtuhcy.iteye.com/blog/2170295 多线程通过final访问主线程局部变量
     * d）https://www.cnblogs.com/bellkosmos/p/5340711.html  有关线程安全的探讨--final、static、单例、线程安全
     * e）https://blog.didispace.com/idea-debug-skills-3/ idea多线程调试
     * f）https://www.cnblogs.com/54chensongxia/p/14388029.html debug的技巧以及重要性（***很有参考意义***）
     */

    /**
     * debug调试唤醒（断点：Suspend改为Thread）
     */
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("1. 我是张三！");
        }, "张三的线程").start();

        new Thread(() -> {
            System.out.println("2. 我是李四！");
        }, "李四的线程").start();

        System.out.println("3. 你好吗！");
        System.out.println("4. 我很好！");

        /**
         * 输出结果：（每次运行，可能会不同）
         * 1. 我是张三！
         * 3. 你好吗！
         * 2. 我是李四！
         * 4. 我很好！
         *
         * 结果分析：
         * 1）将断点类型改为All，所有的线程都会阻塞到此处，也就是看到的是一个一个线程调试，而将类型改为Thread，
         *    就可以各个线程切换着调试。
         *
         * 2）Suspend若勾选中：
         *   All（默认）：阻塞该程序内所有线程
         *   Thread：只阻塞当前断点所在线程
         *
         * 引用网上文章：
         * 断点调试有多重要？
         * 俗话说编码5分钟，debug2小时，从这句话就能体现出调试的重要性，毕竟它占据你“大部分”的时间。
         * 为了真实的体现出它的重要性，“引经据典”，找来了几个资深行业经验的大佬用引用他们的话来表述：
         *    调试技巧比编码技巧更为重要，因为花费在调试上的时间往往比编码还多，学到的东西比编码中学到的更丰富
         *    调试技能重要性甚⾄超过学习⼀门语⾔
         *    不会调试的程序员，肯定编制不出任何好的软件
         */
    }


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
     * 理解：就是让加入的线程先执行完，再执行当前线程，当前线程先进入阻塞状态
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
    public void test_yield() throws IOException { //Done
        YieldTest yt1 = new YieldTest("张三");
        YieldTest yt2 = new YieldTest("李四");
        yt1.start();
        yt2.start();
        System.in.read(); //需要加上这句代码，不然当线程yield让步休眠后，就看不到后续线程重新获取CPU时间片，执行的结果了

        /**
         * 结果输出（每次运行都有不能的结果输出，哪个线程抢到时间片，就对应执行哪个线程）
         *
         * 结果一：
         * ......
         * 李四----30
         * 张三----14
         * 张三----15
         * .....
         *
         * 结果二：
         * ......
         * 张三----30
         * 李四----1
         * 李四----2
         * ......
         *
         * 结果分析：
         * 1）两个线程启动后，在i递增到i == 30时，就会执行yield()，线程让出CPU时间片，重新进入竞争
         *    若线程再次获取到时间片时，就会继续执行。
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

    /**
     * 场景5：线程的终止
     * 1）正常运行结束
     * 2）使用退出标志退出线程
     * 3）使用interrupt方法终止线程
     * 4）使用stop方法终止线程
     *   （会造成不可预料的后果，不安全，不推荐）
     */
    @Test
    public void test_stop_by_flag() { //通过退出标志退出线程
        for (int i = 0; i < 5; i++) {
            ThreadSafeV1 threadSafeV1 = new ThreadSafeV1();
            if (i >= 3) {
                threadSafeV1.exit = true; //更改退出标志
            }
            threadSafeV1.start();
        }
    }

    public class ThreadSafeV1 extends Thread {
        public volatile boolean exit = false;
        public void run() {
            while (!exit) { //通过退出标志，判断是否执行
                System.out.println("执行ThreadSafeV1线程");
                break; //避免无限循环，一个线程就运行一次
            }
        }
    }

    @Test
    public void test_stop_by_interrupt() { //通过interrupt方法终止线程
        for (int i = 0; i < 5; i++) {
            ThreadSafeV2 threadSafeV2 = new ThreadSafeV2();
            threadSafeV2.start();
        }
    }

    /**
     * 场景6：多线程debug方式
     *
     */

    public class ThreadSafeV2 extends Thread {
        public void run() {
            while (!isInterrupted()) { //在非阻塞过程中通过判断中断标志来退出
                try {
                    System.out.println("执行ThreadSafeV2线程");
                    Thread.sleep(100); //在阻塞过程中捕获中断异常来退出
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break; //在捕获到异常后执行break跳出循环
                }
            }
        }
    }
}
