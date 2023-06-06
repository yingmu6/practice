package relative.thread.pool;

import lombok.SneakyThrows;
import org.junit.Test;

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
     * 场景2：使用Executors创建线程池
     */
    @Test
    public void test_create_by_executors() {
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
     */

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

    private static int num = 0;
    // 实现Callable有返回值
    class CallableTest implements Callable<Integer> {
        @Override
        public Integer call() {
            return num++;
        }
    }
}
