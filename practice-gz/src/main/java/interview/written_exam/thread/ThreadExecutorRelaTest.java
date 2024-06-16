package interview.written_exam.thread;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chensy
 * @date 2023/9/13
 */
public class ThreadExecutorRelaTest { //@MsY-Doing

    /**
     * 知识点：ExecutorService线程池
     *
     * 知识点概括：
     * 1）ThreadPoolExecutor中以Worker为单位对工作线程进行管理
     *
     * 2）线程池任务运行的主流程
     *    -> 线程池调用execute提交任务
     *    —> 创建Worker（设置属性thead、firstTask）
     *    —> worker.thread.start()
     *    —> 实际上调用的是worker.run()
     *    —> 线程池的runWorker(worker)
     *    —> worker.firstTask.run()
     *  （最终调用最开始传进来的任务的run方法，不过通过等待队列可以重复利用worker与worker中的线程，变化的只是firstTask）
     *
     * 3）ThreadPoolExecutor.Worker就是线程池中执行任务的类，其继承了AQS并实现Runnable，所以它可以拥有AQS与Runnable的作用。
     *
     * 4）Worker继承了AbstractQueuedSynchronizer，主要目的有两个：
     *   a）将锁的粒度细化到每个工Worker。
     *   b）直接使用CAS获取，避免阻塞
     *
     * 参考链接：
     * a）https://www.cnblogs.com/zjfjava/p/13909285.html 线程池ThreadPoolExecutor——Worker源码解析
     * b）https://juejin.cn/post/6926471351452565512 ThreadPoolExecutor源码解析
     * c）https://cloud.tencent.com/developer/article/1529634 Java线程池的四种用法与使用场景
     */

    /**
     * 场景1：创建一个单线程的线程池，newSingleThreadExecutor
     */
    @Test
    public void test_newSingleThreadExecutor() {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        pool.execute(new MyThread());
        pool.execute(new MyThread());
        pool.execute(new MyThread());
        pool.shutdown();

        /**
         * 输出结果：
         * Thread id = 14，run
         * Thread id = 14，run
         * Thread id = 14，run
         *
         * 结果分析：
         * 1）创建只有一个线程的线程池，等同于newFixedThreadPool(1)，从运行结果看，提交3次任务，都是由同一个线程处理的
         *
         * 问题点答疑：
         * 1）线程池是怎么确保只有一个线程的？
         *    解答：ThreadPoolExecutor#execute提交任务时，会判断运行数与核心数关系，newSingleThreadExecutor指定的是corePoolSize=1
         *         所以在添加第一个线程后，后面不再添加线程。
         *
         * 结果总结：
         * 1）创建一个单线程的线程池，线程池只会用一个工作线程来执行任务，相当于单线程执行所有的任务。
         */
    }

    /**
     * 场景2：创建一个定长线程池
     */
    @Test
    public void test_newFixedThreadPool() {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.execute(new MyThread());
        pool.execute(new MyThread());
        pool.execute(new MyThread());
        pool.execute(new MyThread());
        pool.shutdown();

        /**
         * 运行结果：
         * Thread id = 14，run
         * Thread id = 14，run
         * Thread id = 14，run
         * Thread id = 16，run
         *
         * 结果分析：
         * 1）从运行结果来看，提交的任务由固定的两个工作线程来运行
         *
         * 结果总结：
         * 1）创建固定长度的线程池，内部使用new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
         *    可看出核心数和最大线程数为传入的数量值
         */
    }

    /**
     * 场景3：创建线程数不受限制的线程池（按需创建线程，线程数不做限制）
     */
    @Test
    public void test_newCachedThreadPool() {
        ExecutorService pool = Executors.newCachedThreadPool();
        pool.execute(new MyThread());
        pool.execute(new MyThread());
        pool.execute(new MyThread());
        pool.execute(new MyThread());
        pool.shutdown();

        /**
         * 运行结果：
         * Thread id = 11，run
         * Thread id = 13，run
         * Thread id = 15，run
         * Thread id = 17，run
         *
         * 结果分析：
         * 1）不会对线程数大小做限制，线程池的参数为: 核心数：0, 最大数：Integer.MAX_VALUE, 存活时间：60L, TimeUnit.SECONDS
         *    也就是提交任务时，因为核心数为0，不会在核心线程池中创建线程，而是直接放入阻塞队列，然后根据存活时间回收线程
         *
         */
    }

    /**
     * 场景4：创建一个定长线程池，此线程池支持定时以及周期性
     */
    @Test
    public void test_newScheduledThreadPool() throws IOException {

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
        executor.scheduleWithFixedDelay(new MyThread2(), 0, 3000, TimeUnit.MILLISECONDS);
        executor.scheduleWithFixedDelay(new MyThread2(), 0, 2000, TimeUnit.MILLISECONDS);

        System.in.read();

        /**
         * 运行结果：
         * Thread id = 16，timestamp = 1694572301877
         * Thread id = 14，timestamp = 1694572301877
         * Thread id = 16，timestamp = 1694572303879
         * Thread id = 14，timestamp = 1694572304879
         * Thread id = 16，timestamp = 1694572305882
         * Thread id = 14，timestamp = 1694572307882
         * ......
         *
         * 结果分析：
         * 1）按指定的时间间隔执行周期性任务
         *
         * 问题点答疑：
         * 1）定时任务，最底层是用什么执行的？
         *    解答：底层是通过延迟队列DelayQueue来实现周期性任务的
         */
    }

    class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("Thread id = " + Thread.currentThread().getId() + "，run");
        }
    }

    class MyThread2 extends Thread {

        @Override
        public void run() {
            System.out.println("Thread id = " + Thread.currentThread().getId() + "，timestamp = " + System.currentTimeMillis());
        }
    }
}
