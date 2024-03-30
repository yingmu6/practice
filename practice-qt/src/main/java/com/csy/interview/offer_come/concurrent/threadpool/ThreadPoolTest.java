package com.csy.interview.offer_come.concurrent.threadpool;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * @author chensy
 * @date 2024/3/23
 */
public class ThreadPoolTest {

    /**
     * 知识点：线程池
     *
     * 知识点概括：
     *
     * 问题点答疑：
     * 1）什么情况下，会进入拒绝线程池拒绝策略？
     *    解答：源码注释：If the task cannot be submitted for execution, either because this
     *    executor has been shutdown or because its capacity has been reached（即任务不能提交到线程池时会执行受拒策略）
     *
     * 2）测试用例，怎么模拟进入到线程池拒绝策略中
     */

    /**
     * 场景1：自定义拒绝策略
     */
    @Test
    public void customRejectedStrategy() throws IOException {
        int corePoolSize = 1;
        int maximumPoolSize = 1;
        long keepAliveTime = 2;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue(2);
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) { //重写线程池中创建线程的方法，可做一些定制化操作，比如设置线程名称等，如org.apache.dubbo.common.utils.NamedThreadFactory
                return new Thread(r);
            }
        };
        RejectedExecutionHandler handler = new DiscardOldestNPolicy(3);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime
                , unit, workQueue, threadFactory, handler);

        Thread t1 = new Thread(() -> {
            System.out.println("thread 1 run");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(() -> System.out.println("thread 2 run"));

        executor.submit(t1);
        executor.submit(t2);
        System.in.read();

        /**
         * 输出结果：
         *
         * 结果分析：
         *
         * 结果概括：
         */
    }

    /**
     * 场景2：线程池的创建方式
     */
    @Test
    public void createThreadPool() {
        // 方式一：
        ExecutorService e1 = Executors.newCachedThreadPool();

        // 方式二：
        ExecutorService e2 = Executors.newFixedThreadPool(5);

        // 方式三：
        ExecutorService e3 = Executors.newSingleThreadExecutor();

        // 方式四：
        ExecutorService e4 = Executors.newWorkStealingPool();
    }
}
