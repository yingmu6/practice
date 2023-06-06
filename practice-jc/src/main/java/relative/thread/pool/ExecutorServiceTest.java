package relative.thread.pool;


import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 线程池的抽象接口： ExecutorService
 * @author : chensy
 * Date : 2020-02-29 21:28
 */
public class ExecutorServiceTest {

    /**
     * 线程池的抽象接口概述：（ExecutorService是Java中对线程池定义的一个接口，它java.util.concurrent包中，在这个接口中定义了和后台任务执行相关的方法。）
     *
     * 1）An Executor that provides methods to manage termination and
     *  methods that can produce a Future for tracking progress of one or more asynchronous tasks.
     *  （ExecutorService：定义了和后台任务执行相关的方法，且产生Future的线程池的方法，并且能跟踪一个或多个异步任务）
     *
     * 2）ExecutorService is a JDK API that simplifies running tasks in asynchronous mode. Generally speaking,
     *   ExecutorService automatically provides a pool of threads and an API for assigning tasks to it.
     *  （ExecutorService：是一个简化异步模式下运行任务的JDK API。一般来说, ExecutorService自动提供一个线程池和一个API来分配任务）
     *
     * 3）Executors：为创建线程池提供了工厂、实用的方法
     *
     * 4）We can assign（分配） tasks to the ExecutorService using several methods including execute(), which is inherited
     * from the Executor interface, and also submit(), invokeAny() and invokeAll().
     * （我们可以使用几个方法将任务分配给ExecutorService，包括execute()，它继承自Executor接口，还有submit()， invokeAny()和invokeAll()。）
     *
     * 5）In general, the ExecutorService will not be automatically destroyed when there is no task to process.
     *    It will stay alive and wait for new work to do.
     * （通常情况下，ExecutorService在没有任务处理时不会自动销毁。它将保持活力，等待新的工作完成 ---所以线程池，在使用完成后要及时销毁）
     *
     * 6）The shutdown() method doesn't cause immediate destruction of the ExecutorService. It will make the ExecutorService stop
     * accepting new tasks and shut down after all running threads finish their current work:
     * （shutdown()方法不会立即销毁ExecutorService。它将使ExecutorService停止接受新任务，并在所有正在运行的线程完成当前工作后关闭:）
     *
     * 7）The ScheduledExecutorService runs tasks after some predefined delay and/or periodically.
     * （ScheduledExecutorService 预定义地运行 延迟和/或周期性的任务）
     *
     * 8）ThreadPoolExecutor是ExecutorService接口的一个实现类，它提供了一个线程池，可以用于执行异步任务。
     *    ExecutorService是一个更为抽象的接口，它定义了一些用于异步任务处理的方法。
     *    （总之，ThreadPoolExecutor是ExecutorService的一个具体实现，它提供了更加底层的控制和配置。
     *      而ExecutorService则是一个更为抽象的接口，它可以用于处理各种类型的异步任务。）
     *
     *
     * 参考链接：
     * a）https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html 官网ExecutorService API
     * b）https://www.baeldung.com/java-executor-service-tutorial ExecutorService使用
     * c）https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/Executors.html  Executors API
     * d）https://juejin.cn/s/threadpoolexecutor%20executorservice%20%E5%8C%BA%E5%88%AB ThreadPoolExecutor与ExecutorService的区别
     * e）https://www.baeldung.com/thread-pool-java-and-guava （Introduction to Thread Pools in Java）
     */

    /**
     * 场景1：基本使用
     */
    @Test
    public void test_basic() {
        // Creates a thread pool that reuses（再次使用） a fixed number(固定数量) of threads operating off a shared unbounded queue（无界队列）.
        // 创建一个固定数量的线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2); //newFixedThreadPool内部使用ThreadPoolExecutor实现
        fixedThreadPool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("固定数量的线程池");
            }
        });

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("不限数量的线程池");

            }
        });
    }

    /**
     * 场景2：提交任务到线程池的几种方式
     */
    @Test
    public void test_way_v1() {
        Runnable runnableTask = () -> {
            System.out.println("execute方式: 进入Runnable任务");
        };
        // 方式一：使用execute方法
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(runnableTask);
    }

    @Test
    public void test_way_v2() {
        Callable<String> callableTask = () -> {
            System.out.println("submit方式：进入Callable任务");
            return "Task's execution";
        };

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // 方式二：使用submit方法
        executorService.submit(callableTask);
    }

    @Test
    public void test_way_v3() throws ExecutionException, InterruptedException {

        Callable<String> callableTask = () -> {
            TimeUnit.MILLISECONDS.sleep(300);
            System.out.println("进入Callable任务");
            return "Task's execution";
        };

        List<Callable<String>> callableTasks = new ArrayList<>();
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // 方式三：
        String result = executorService.invokeAny(callableTasks);
        System.out.println("invokeAny输出结果：" + result);

        // 方式四：
        List<Future<String>> futures = executorService.invokeAll(callableTasks);
        System.out.println("invokeAll输出结果：" + futures);
    }

    /**
     * 场景3：线程池停用方法_shutdown()和shutdownNow()
     */
    @Test
    public void test_shutdown() {
        Runnable runnableTask = () -> System.out.println("execute方式: 测试shutdown");

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(runnableTask);

        System.out.println("线程池是否停止_v1：" + executorService.isShutdown()); //输出false（线程池不会自动销毁，即使线程池中没有运行的任务）
        executorService.shutdownNow();
        System.out.println("线程池是否停止_v2：" + executorService.isShutdown()); //输出true（调用shutdownNow()，会立即销毁线程池）
    }

    /**
     * 场景4：线程池等待终止方法_awaitTermination
     */
    @Test
    public void test_awaitTermination() {
        Runnable runnableTask = () -> {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage() + "," + e.getStackTrace());
            }
            System.out.println("execute方式: 测试awaitTermination");
        };

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(runnableTask);
        /**
         * 此处分析：
         * 1）若awaitTermination等待时间为200ms，runnableTask的sleep时间为600ms，awaitTermination在200ms后，会调用executorService.shutdownNow();所以runnableTask会抛出"sleep interrupt";
         * 2）若awaitTermination等待时间为800ms，runnableTask的sleep时间为600ms，runnableTask可以完全运行完成
         */
        executorService.shutdown(); //停止线程池
        try {
            if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) { //阻塞等待任务执行指定的时间，如此处：阻塞等待800毫秒后，执行相应的逻辑
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }

    /**
     * 场景5：使用返回的Future，来获取异步结果
     */
    @Test
    public void test_future() {
        Callable<String> callableTask = () -> "Result of asynchronous execution";
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Future<String> future = executorService.submit(callableTask); //获取到任务结果对应的Future
        String result = null;
        try {
            result = future.get(); //阻塞获取结果
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("响应的结果：" + result);
    }

    /**
     * 场景6：预定义的线程池_ScheduledExecutorService
     */
    @Test
    public void test_scheduled_executorService() throws IOException {
        long currentTime = System.currentTimeMillis();

        Runnable runnableTask = () -> System.out.println("execute方式: 测试shutdown, 耗时：" + (System.currentTimeMillis() - currentTime));
        ScheduledExecutorService executorService = Executors
                .newSingleThreadScheduledExecutor();

        /**
         * 执行任务的时间：
         * 1）第一次执行任务时间：在100ms后
         * 2）第二次执行任务时间：在100ms + 450ms
         * 3）第三次执行任务时间：在100ms + 2 * 450ms
         * 4）第N次执行任务时间：在100ms + (n - 1) * 450ms
         * 按延迟时间倍数执行
         */
//        executorService.scheduleAtFixedRate(runnableTask, 100, 450, TimeUnit.MILLISECONDS);

        /**
         * 执行任务的时间：
         * 1）第一次执行时间：在100ms以后
         * 2）第二次执行时间：在100ms + 150ms后
         * 3）第三次执行时间：在100ms + 150ms + 150ms后
         * 后面每次按固定延迟时间执行
         */
        executorService.scheduleWithFixedDelay(runnableTask, 100, 150, TimeUnit.MILLISECONDS);

        System.in.read(); //此处为了让方法不终止，看到运行的效果
    }

}
