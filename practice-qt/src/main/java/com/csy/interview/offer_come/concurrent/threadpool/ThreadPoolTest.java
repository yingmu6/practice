package com.csy.interview.offer_come.concurrent.threadpool;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @author chensy
 * @date 2024/3/23
 */
public class ThreadPoolTest {

    /**
     * 知识点：线程池
     *
     * 知识点概括：
     * 1）线程池内部会创建自己运行的线程Worker，该线程持有FutureTask的引用，FutureTask又持有提交到线程池的任务的引用
     * 2）任务提交到线程池的工作流程：
     *    a）判断当前工作的线程Worker数量是否超过线程核心数，若没有超过，则直接创建一个Worker线程执行提交的任务
     *    b）若超过了线程核心数，则将任务提交到阻塞队列中，暂时做任务缓存（Worker线程会一直循环执行任务，当任务执行完，主动去队列中获取任务执行）
     *    c）在提交任务到阻塞队列时，若阻塞队列已经满了，则判断是否超过最大线程数，若没有超过则创建线程执行任务（这部分线程受存活时间的约束），超过了则触发受拒策略
     *
     * 问题点答疑：
     * 1）什么情况下，会进入拒绝线程池拒绝策略？
     *    解答：源码注释：If the task cannot be submitted for execution, either because this
     *    executor has been shutdown or because its capacity has been reached（即任务不能提交到线程池时会执行受拒策略）
     *
     * 2）测试用例，怎么模拟进入到线程池拒绝策略中
     *
     * 参考链接：
     * a）https://juejin.cn/post/6946087172143317023 Java线程池源码解析（讲解得比较形象）
     */

    /**
     * 场景1：自定义拒绝策略
     */
    @Test
    public void customRejectedStrategy() throws IOException, ExecutionException, InterruptedException {
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

        Callable callable = new Callable<String>() {

            @Override
            public String call() throws Exception {
                return "hello";
            }
        };

        executor.submit(t1);
        executor.submit(t2);
        Future future = executor.submit(callable);
        System.out.println("获取异步结果：" + future.get());

        System.in.read();

        /**
         * 输出结果：
         * thread 1 run
         * thread 2 run
         * 获取异步结果：hello
         *
         * 结果分析：
         * 1）先指定ThreadPool的相关参数，如核心数、最大线程数、线程工厂、受拒策略等
         * 2）然后就可以提交任务到线程池，任务可以是Runnable，也可以是Callable形式
         * 3）提交任务后，线程池会返回Future引用，可通过这个引用去获取异步的结果
         *
         * 结果概括：
         * 1）线程池内部是将任务和运行的线程分隔开，使用阻塞队列来做任务的缓存
         *
         */
    }

    /**
     * 场景2：线程池的创建方式
     */
    @Test
    public void createThreadPool() throws IOException {
        // 方式一：
        ExecutorService e1 = Executors.newCachedThreadPool();
        e1.submit(() -> System.out.println("newCachedThreadPool方式：创建线程池1"));
        e1.submit(() -> System.out.println("newCachedThreadPool方式：创建线程池2"));

        // 方式二：
//        ExecutorService e2 = Executors.newFixedThreadPool(5);
//        e2.submit(() -> System.out.println("newFixedThreadPool方式：创建线程池"));
//
//        // 方式三：
//        ExecutorService e3 = Executors.newSingleThreadExecutor();
//        e3.submit(() -> System.out.println("newSingleThreadExecutor方式：创建线程池"));
//
//        // 方式四：
//        ExecutorService e4 = Executors.newWorkStealingPool();
//        e4.submit(() -> System.out.println("newWorkStealingPool方式：创建线程池"));

        System.in.read();

        /**
         * 输出结果：
         * newCachedThreadPool方式：创建线程池
         * newFixedThreadPool方式：创建线程池
         * newSingleThreadExecutor方式：创建线程池
         * newWorkStealingPool方式：创建线程池
         *
         * 结果分析：
         *
         *
         * 结果概括：
         */
    }

    /**
     * 场景3：模拟输出线程受拒策略
     */
    @Test
    public void basicTest() {
        RejectedExecutionHandler handler = new DiscardOldestNPolicy(3);

        ExecutorService executors = new ThreadPoolExecutor(2,
                3,
                10, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(4),handler);

        IntStream.range(0, 10).forEach(i -> {
            executors.submit(() -> {
                IntStream.range(0, 50).forEach(j -> System.out.println(Thread.currentThread().getName()));
            });
        });

        executors.shutdown();

        /**
         * 输出结果：
         *
         * 结果分析：
         *
         * 结果概括：
         */
    }

    /**
     * 场景4：捕获提交任务时，发生的异常
     */
    @Test
    public void catchThreadException() throws IOException, ExecutionException, InterruptedException {
        ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        Future future = poolExecutor.submit(() -> { //异常信息不会抛出
            Integer a = null;
            System.out.println(a.intValue());
        });

        System.out.println(future.get()); //通过get方法时，抛出了异常信息


        ThreadPoolExecutor poolExecutor2 = (ThreadPoolExecutor) Executors.newCachedThreadPool();
//        poolExecutor2.

        System.in.read();
    }

    public static void main(String[] args) {
        Integer a = null;
        System.out.println(a.intValue());
    }
}
