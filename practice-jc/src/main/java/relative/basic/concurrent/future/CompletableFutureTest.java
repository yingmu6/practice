package relative.basic.concurrent.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author chenSy
 * @Date 2023/01/16 22:28
 * @Description
 */
public class CompletableFutureTest {
    public static void main(String[] args) throws Exception {
        // basicUse();
        useRunAsync();
    }

    public static void basicUse() throws Exception { //基本使用
        Future<String> future = calculateAsync(new CompletableFuture<>());
        String result = future.get(); //阻塞获取结果
        System.out.println("输出的结果：" + result + ",时间戳：" + System.currentTimeMillis() / 1000);

        int a = 1;
        System.out.println("另外的任务：" + a + ",时间戳：" + System.currentTimeMillis() / 1000); //从运行的结果来看，线程会阻塞，要等前面运行好，当前语句才会执行
    }

    public static void useRunAsync() throws Exception { //使用runAsync  todo @pause
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Hello";
        });

        System.out.println("输出的结果2：" + future.get() + ",时间戳：" + System.currentTimeMillis() / 1000); //也是阻塞着等结果

        int a = 1;
        System.out.println("另外的任务2：" + a + ",时间戳：" + System.currentTimeMillis() / 1000);
    }

    public static Future<String> calculateAsync(CompletableFuture<String> completableFuture) throws InterruptedException {

        Executors.newCachedThreadPool().submit(() -> { //使用线程池异步执行
            Thread.sleep(5000);
            completableFuture.complete("Hello");
            return null;
        });

        return completableFuture;
    }

    /**
     * Future是比较难知道任务已经完成的，需要阻塞或轮询获取结果
     * Asynchronous computation is difficult to reason about
     *
     * but it did not have any methods to combine these computations or handle possible errors.
     * （Future没有任何方法用来绑定完成或异常操作）
     *
     * First, the CompletableFuture class implements the
     * Future interface so that we can use it as a Future implementation but with additional completion logic.
     * （实现了Future，可以作为Future的额外功能）
     *
     * Static methods runAsync and supplyAsync allow us to create a CompletableFuture instance out of
     * Runnable and Supplier functional types correspondingly.（runAsync与supplyAsync能异步吗）
     *
     * https://www.baeldung.com/java-completablefuture  英文版介绍
     */
}
