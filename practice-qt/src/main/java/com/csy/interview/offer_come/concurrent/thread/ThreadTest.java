package com.csy.interview.offer_come.concurrent.thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author chensy
 * @date 2024/3/23
 */
public class ThreadTest {

    /**
     * 场景1：通过继承Thread的方式创建线程
     */
    @Test
    public void createByExtendThread() {
        NewThread newThread = new NewThread();
        newThread.start();
    }

    /**
     * 场景2：通过实现Runnable接口的方式创建线程
     */
    @Test
    public void createByImplementRunnable() {
       ChildThread childThread = new ChildThread();
       Thread thread = new Thread(childThread);
       thread.start();
    }

    /**
     * 场景3：通过ExecutorService和Callable实现有返回值的线程
     */
    @Test
    public void useExecutorAndCallable() throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        List<Future> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Callable c = new MyCallable(i + "");
            Future future = pool.submit(c);
            System.out.println("submit a callable thread:" + i);
            list.add(future);
        }

        pool.shutdown();
        for (Future future : list) {
            System.out.println("get the result from callable thread:" + future.get().toString());
        }
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
        NewThread newThread = new NewThread();
        newThread.join();
        System.out.println("子线程join()结束，开始运行主线程");
    }

    /**
     * 场景6：终止线程的4种方式
     * 1）正常运行结束
     * 2）使用退出标志退出线程
     * 3）使用interrupt方法终止线程
     * 4）使用stop方法终止线程：不安全
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
