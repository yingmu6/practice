package relative.thread.pool;

import java.util.concurrent.*;

/**
 * 线程池使用
 * @author : chensy
 * Date : 2020-01-15 19:35
 */
public class ThreadPoolExecutorTest {
    private static int num = 0;

    public static void main(String[] args) {
         selfDefine();
        //executors();
    }

    /**
     * todo @chenSy 问题集
     * 1：BlockingQueue在线程池有啥用？
     * 2：怎样验证线程池的效果？
     * 3：keepAliveTime的用途？
     * 4.怎样模拟
     */
    private static void selfDefine() {
        int corePoolSize = 2;
        int maximumPoolSize = 3;
        long keepAliveTime = 1L;
        // 时间单位，枚举值
        TimeUnit timeUnit = TimeUnit.SECONDS;
        // fair对访问顺序处理，true表示按FIFO顺序处理
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(3, true);
//        workQueue.add();
//        workQueue.add(new ThreadTest());
        // 自定义线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, timeUnit, workQueue);

        executor.execute(createThread("线程1111"));
        executor.execute(createThread("线程2222"));
        executor.execute(createThread("线程3333"));
        executor.execute(createThread("线程4444"));
        executor.execute(createThread("线程5555"));

    }

    /**
     * 1）corePoolSize留在线程池中的线程个数，也就是工作的线程个数，影响workers属性的值，HashSet<Worker> workers = new HashSet<Worker>();
     * 2）
     */

    static Thread createThread(String name) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(name);
            }
        });
        thread.setName(name);
        return thread;
    }

    // 实现Runnabale没有返回值
    static class RunnableTest implements Runnable {
        @Override
        public void run() {
            System.out.println(num++);
        }
    }

    // 实现Callable有返回值
    static class CallableTest implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            return num++;
        }
    }

    // 使用Executors工具创建线程池，Executor用来管理线程的使用和调度
    private static void executors() {
        // ExecutorService是一个Executor ，提供方法来管理终端和方法，可以产生Future为跟踪一个或多个异步任务执行。
        ExecutorService service = Executors.newFixedThreadPool(3);
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


}
