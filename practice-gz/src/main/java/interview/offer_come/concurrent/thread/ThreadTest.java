package interview.offer_come.concurrent.thread;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author chensy
 * @date 2024/3/23
 */
public class ThreadTest { //@MsY-Done

    /**
     * 知识点：线程
     *
     * 知识点概括：
     * 1）创建Thread对象时，内部会初始化线程的相关信息，如：线程名称，线程分组，线程优先级，线程ID等
     * 2）线程的run()是由JVM虚拟机调用的，使用者只需调用线程的start()，待线程获取到运行资源时，由虚拟机调用。
     * 3）使用者不能手动调用run()方法，若手动调用，就是普通的调用方式，不是线程调用了
     * 4）实现Runnable接口的run()后，就指定了线程的执行逻辑，然后Runnable作为目标对象传入给Thread，再启动start线程
     * 5）线程池相关信息：
     *    5.1）线程池中运行的线程为Worker，Worker继承AQS且实现Runnable接口，也就是可以执行加锁的线程的，它内部持有FutureTask的引用，而FutureTask又持有提交任务的Callable引用
     *    5.2）当提前任务到线程池时，会先判断线程池中工作的Worker数是否超过corePoolSize，若没有超过，直接创建一个Worker执行任务，超过了就把任务先缓存到阻塞队列中
     *    5.3）若阻塞队列已经满了，看下运行的Worker数是否超过最大线程数，若超过则执行受拒策略，没超过则创建Worker线程执行任务，但新建的线程会根据keepLiveTime进行超时回收
     * 6）终止线程的4种方法：
     *    6.1）正常运行结束
     *    6.2）使用退出标志退出线程
     *    6.3）使用interrupt方法终止线程
     *    6.4）使用stop方法终止线程：不安全
     *
     * 问题点答疑：
     * 参考链接：线程池原理：https://tech.meituan.com/2020/04/02/java-pooling-pratice-in-meituan.html
     */


    /**
     * 场景1：通过继承Thread的方式创建线程
     */
    @Test
    public void createByExtendThread() throws IOException {
        NewThread newThread = new NewThread();
        newThread.start();

        /**
         * 输出结果：
         * create a thread by extends Thread
         *
         * 结果分析：
         * 1）通过继承Thread类，并重写run()，实现线程的创建
         *
         */
        System.in.read(); //若不加此句，测试用例所在的主线程就会停止，若想在NewThread的run()断点时，就看不到效果了
    }

    /**
     * 场景2：通过实现Runnable接口的方式创建线程
     */
    @Test
    public void createByImplementRunnable() {
       ChildThread childThread = new ChildThread();
       Thread thread = new Thread(childThread);
       thread.start();

        /**
         * 输出结果：
         * create a thread by implements Runnable
         *
         * 结果分析：
         * 1）通过实现Runnable接口，然后作为持有run()的目标对象交由线程执行
         */
    }

    /**
     * 场景3：通过ExecutorService和Callable实现有返回值的线程
     */
    @Test
    public void useExecutorAndCallable() throws ExecutionException, InterruptedException, IOException {
        ExecutorService pool = Executors.newFixedThreadPool(1); //创建固定大小的线程池，即核心数和最大数相同（线程固定不会收缩）
        List<Future> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Callable c = new MyCallable(i + ""); //Callable：可以返回结果的任务
            Future future = pool.submit(c);
            System.out.println("submit a callable thread:" + i);
            list.add(future);
        }

//        pool.shutdown();
        for (Future future : list) {
            System.out.println("get the result from callable thread:" + future.get().toString());
        }

        System.in.read();

        /**
         * 输出结果：
         * submit a callable thread:0
         * submit a callable thread:1
         * submit a callable thread:2
         * get the result from callable thread:0
         * get the result from callable thread:1
         * get the result from callable thread:2
         *
         * 结果分析：
         * 1）MyCallable实现了Callable接口，即为可返回结果的线程，将其提交到线程池，每次提交都会得到一个FutureTask对象实例
         * 2）FutureTask实现了Runnable、Future接口，内部通过FutureTask调用提交任务的run()方法，并将结果存起来。
         *
         */
    }

    /**
     * 场景4：创建线程，并提交到线程池中
     */
    @Test
    public void submitThreadToPool() {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "is running");
                }
            });
        }
    }

    /**
     * 场景5：线程的基本方法
     */
    @Test
    public void basicThreadMethod() {
        // 线程等待：wait方法

        // 线程睡眠：sleep方法

        // 线程让步：yield方法

        // 线程中断：interrupt方法

        // 线程加入：join方法

        // 线程唤醒：notify方法

        // 后台守护线程：setDaemon方法
    }

    @Test
    public void threadMethod_interrupt() {
        SafeInterruptThread thread = new SafeInterruptThread();
        thread.interrupt();
    }

    @Test
    public void threadMethod_join() throws InterruptedException {
        System.out.println("子线程运行开始！");
        long startTime = System.currentTimeMillis();
        NewThread2 thread2 = new NewThread2();
        thread2.start(); //注意：若没有start()启动线程，则join()方法无效

        thread2.join();
        System.out.println("子线程join()结束，开始运行主线程，耗时：" + (System.currentTimeMillis() - startTime) / 1000 + " 秒");

        /**
         * 输出结果：
         * 子线程运行开始！
         * NewThread2 run()
         * 子线程join()结束，开始运行主线程，耗时：5 秒
         *
         * 结果分析：
         * 1）调用了线程的join()，则加入一个线程，即等待加入的线程完成后，才执行当前线程
         */
    }

    /**
     * 场景6：终止线程的4种方式
     */
    @Test
    public void stopThreadWay_flag() {
         // ThreadSafe
    }

    @Test
    public void stopThreadWay_interrupt() {
        // ThreadSafe2
    }
}
