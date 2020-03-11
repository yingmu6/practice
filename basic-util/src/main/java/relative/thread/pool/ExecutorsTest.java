package relative.thread.pool;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池工具类使用 Executors
 * @author : chensy
 * Date : 2020-02-29 21:28
 */
public class ExecutorsTest {
    public static void main(String[] args) {
        basicUse();
    }

    public static void basicUse() {
        // Creates a thread pool that reuses a fixed number(固定数量) of threads operating off a shared unbounded queue（无界队列）.
        // 创建一个固定数量的线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
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
}
