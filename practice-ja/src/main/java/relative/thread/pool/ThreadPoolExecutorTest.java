package relative.thread.pool;

import lombok.SneakyThrows;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.*;

/**
 * 线程池的实现类 ThreadPoolExecutor
 * @author : chensy
 * Date : 2020-01-15 19:35
 */
public class ThreadPoolExecutorTest {

    /**
     * 线程池的实现类ThreadPoolExecutor概述：
     * （Java线程池，是典型的池化思想的产物，类似的还有数据库的连接池、redis的连接池等。池化思想，就是在初始的时候去申请资源，
     * 创建一批可使用的连接，这样在使用的时候，就不必再进行创建连接信息的开销了。线程池既然是容器，那么必然的会有存满的情况。在达到某些特定条件的时候，再来请求的话，
     * 池子是如何进行请求处理的呢？这里就引出了池的拒绝策略。一般的数据库连接池在达到最大连接数的时候会默认的等待特定的设置的时间或者直接就抛出异常）
     *
     * 1）A thread pool is a collection of pre-initialized threads.（线程池是一个预初始化的线程集合）
     *
     * 2）If there are more tasks than threads, then tasks need to wait in a queue like structure。
     * When any thread completes its execution, it can pick up（拿起） a new task from the queue and execute it.
     * （当超过核心任务数时，会将任务放到队列中。然后当任意线程完成时，就会从队列中拿起任务来执行）
     *
     * 3）A watcher keeps watching the queue (usually BlockingQueue) for any new tasks.
     * As soon as（一...就...） tasks come, threads start picking up tasks and executing them again.
     * （观察者保持观察队列中的新任务，任务一来就拿出任务去执行）
     *
     * 4）The ThreadPoolExecutor class has four different constructors but due to their complexity, the Java concurrency API provides the
     * Executors class to construct executors and other related objects. Although we can create ThreadPoolExecutor directly using one of its constructors,
     * it’s recommended to use the Executors class.
     * （ThreadPoolExecutor有4个不同参数的构造函数，但是由于它的复杂性，Java 并发API提供了Executors来构建线程池。虽然能够直接使用ThreadPoolExecutor来创建，但推荐使用Executors）
     *
     * 5）ThreadPoolExecutor separates（分离） the task creation and its execution. With ThreadPoolExecutor, we only have to implement the Runnable objects and send them to the executor.
     * It is responsible for executing, instantiating, and running the tasks with necessary threads.
     * （ThreadPoolExecutor分离了任务的创建和执行，使用线程池，我们仅仅需要实现Runnable的对象，并把它们发送给线程池即可。线程池负责线程执行、实例化和运行任务）
     *
     * 6）It goes beyond that and improves performance using a pool of threads. When you send a task to the executor, it tries to use a pooled thread to execute this task, to avoid the continuous spawning（生成） of threads.
     * （使用线程能提升性能。当发送一个任务到线程池时，它会尝试使用线程池中的线程来执行任务，避免连续生成线程）
     *
     * 7）One critical（关键的） aspect of the ThreadPoolExecutor class, and of the executors in general, is that you have to end it explicitly. If you don’t do this,
     * the executor will continue its execution, and the program won’t end.
     * （ThreadPoolExecutor类和执行器的一个关键方面是，必须显式地结束它。如果不这样做，执行程序将继续执行，程序不会结束。）
     *
     * 8）The ThreadPoolExecutor class provides a lot of methods to obtain information about its status. We used in the example the getPoolSize(), getActiveCount(), and getCompletedTaskCount()
     * methods to obtain information about the size of the pool。
     * （ThreadPoolExecutor类提供了许多方法来获取有关其状态的信息。我们在示例中使用了getPoolSize()、getActiveCount()和getCompletedTaskCount()方法来获取池大小的信息）
     *
     * 参考链接：
     * a）https://howtodoinjava.com/java/multi-threading/java-thread-pool-executor-example 使用ThreadPoolExecutor的用例
     * b）https://www.educative.io/courses/java-multithreading-for-senior-engineering-interviews/RLg4O1VYJ3w  ThreadPoolExecutor详细参数介绍 （** 重点参考 **）
     */

    /**
     * 场景1：自定义线程池参数
     */
    @Test
    public void test_create_by_custom_params() {
        int corePoolSize = 2;
        int maximumPoolSize = 3;
        long keepAliveTime = 1L;
        // 时间单位，枚举值
        TimeUnit timeUnit = TimeUnit.SECONDS;
        // fair对访问顺序处理，true表示按FIFO顺序处理
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(3, true);
        // 自定义线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, timeUnit, workQueue);

        // 将线程提交到线程池中，由线程池来管理线程
        executor.execute(createThread("线程1111"));
        executor.execute(createThread("线程2222"));
        executor.execute(createThread("线程3333"));
        executor.execute(createThread("线程4444"));
        executor.execute(createThread("线程5555"));
    }

    /**
     * 场景2：使用Executors创建线程池的多种方式：
     * 1）Executors.newCachedThreadPool() - (unbounded 无边界 thread pool, with automatic thread reclamation 收回) //创建无边界的线程池，能自动回收现场
     * 2）Executors.newFixedThreadPool(int) (fixed size thread pool) //创建固定线程数的线程池
     * 3）Executors.newSingleThreadExecutor() (single background thread) //创建只有一个线程的线程池
     * 4）Executors.newScheduledThreadPool(int) (fixed size thread pool supporting delayed and periodic（周期的） task execution.) //创建固定线程数的线程池，并且支持周期的任务执行
     */
    @Test
    public void test_create_by_executors() throws IOException {
        // 方式一：
        ExecutorService service = Executors.newFixedThreadPool(3);
        // 提交任务后返回Future，可用于获取异步任务执行的结果
        Future future1 = service.submit(new CallableTest());
        Future future2 = service.submit(new CallableTest());
        try {
            // 异步获取值
            Object obj1 = future1.get();
            Object obj2 = future2.get();
            System.out.println(obj1 + ";" + obj2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        /**
         * 方式二：内部对应的构造方法为 new ThreadPoolExecutor(0, Integer.MAX_VALUE,
         *                                               60L, TimeUnit.SECONDS,
         *                                              new SynchronousQueue<Runnable>())
         * 用整数的最大值作为 线程池的最大线程数，即可认为是无界
         */
        ExecutorService service2 = Executors.newCachedThreadPool();
        service2.submit(() -> System.out.println("线程2"));

        // 方式三：
        ExecutorService service3 = Executors.newSingleThreadExecutor();
        service3.submit(() -> System.out.println("线程3"));

        // 方式四：
        ScheduledExecutorService service4 = Executors.newScheduledThreadPool(3);
        service4.scheduleWithFixedDelay(() -> System.out.println("test"), 100, 200, TimeUnit.MILLISECONDS);
        System.in.read();
    }

    /**
     * 场景3：自定义线程池类
     */
    @Test
    public void test_custom_threadPool() {
        CustomThreadPool customThreadPool = new CustomThreadPool(2);

        for (int i = 1; i <= 5; i++) {
            Task task = new Task("Task " + i);
            System.out.println("Created : " + task.getName());

            customThreadPool.execute(task);
        }
    }


    /**
     * 场景4：线程池各个参数以及核心逻辑测试
     *
     * 在执行execute()添加一个任务时，会执行如下的流程：
     * 1）如果正在运行的线程数量少于corePoolSize（用户自定义的线程数），线程池就会立即创建线程并执行该线程任务
     * 2）如果正在运行的线程数大于等于corePoolSize，该任务就讲被放入阻塞队列中。
     * 3）在阻塞队列已满且正在运行的线程数量小于maximumPoolSize时，线程池讲拒绝执行该线程任务并抛出RejectExecutionException异常
     * 4）在线程任务执行完毕后，该任务将被从线程池队列中移除，线程池将从队列中取下一个线程任务继续执行
     * 5）在线程处于空闲状态的时间超过keepAliveTime时间时，正在运行的线程数量超过corePoolSize，该线程就会被认定为空闲线程并停止。因此在线程池中所有任务都执行完毕后，
     *    线程池会收缩到corePoolSize的大小
     */
    @Test
    public void test_core_parameters() {

        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(1, true);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(0, 1, 1L, TimeUnit.SECONDS, workQueue);

        // 将线程提交到线程池中，由线程池来管理线程
        executor.execute(createThread("线程1111", 2000L));
        System.out.println("第1次提交，活跃数：" + executor.getActiveCount() + "，阻塞队列中任务数：" + workQueue.size());

        executor.execute(createThread("线程2222", 2000L));
        System.out.println("第2次提交，活跃数：" + executor.getActiveCount() + "，阻塞队列中任务数：" + workQueue.size());

        executor.execute(createThread("线程3333"));
        System.out.println("第3次提交，活跃数：" + executor.getActiveCount() + "，阻塞队列中任务数：" + workQueue.size());
    }

    /**
     * 场景5：测试线程池拒绝策略
     * 1）ThreadPoolExecutor.AbortPolicy 默认拒绝策略，拒绝任务并抛出任务
     * 2）ThreadPoolExecutor.CallerRunsPolicy 使用调用线程直接运行任务
     * 3）ThreadPoolExecutor.DiscardPolicy 直接拒绝任务，不抛出错误
     * 4）4ThreadPoolExecutor.DiscardOldestPolicy 触发拒绝策略，只要还有任务新增，一直会丢弃阻塞队列的最老的任务，并将新的任务加入
     *
     * 总结起来，也就是一句话，当提交的任务数大于（workQueue.size() + maximumPoolSize ），就会触发线程池的拒绝策略。
     */
    @Test
    public void test_reject_policy() {
        int corePoolSize = 5;
        int maximumPoolSize = 10;
        long keepAliveTime = 5;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(10);
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue, handler);
        for(int i = 0; i < 100; i++) {
            try {
                executor.execute(new Thread(() -> System.out.println(Thread.currentThread().getName() + " is running")));
            } catch (Exception e) {
                System.out.println("抛出异常：" + e.getMessage());
            }
        }
        executor.shutdown();
    }

    class Task implements Runnable {
        private final String name;

        public Task(String name) {
            this.name = name;
        }

        @SneakyThrows
        @Override
        public void run() {
            Thread.sleep(2000l);
            System.out.println("Task [" + name + "] executed on : " + LocalDateTime.now().toString());
        }

        public String getName() {
            return this.name;
        }
    }

    private Thread createThread(String name) {
        Thread thread = new Thread(() -> System.out.println("线程名：" + name));
        thread.setName(name);
        return thread;
    }

    private Thread createThread(String name, Long time) {

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Thread thread = new Thread(() -> System.out.println("线程名：" + name));
        thread.setName(name);
        return thread;
    }

    private static int num = 0;
    // 实现Callable有返回值
    class CallableTest implements Callable<Integer> {
        @Override
        public Integer call() {
            return num++;
        }
    }
}
