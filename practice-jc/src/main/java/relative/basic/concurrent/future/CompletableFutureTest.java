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
//        useRunAsync();
//        useAsyncDeal(true);
        useAsyncDeal(false);
    }

    /**
     * 场景1：解决Future处理不能结束的任务
     */

    /**
     * 场景2：解决Future处理多个任务，且有前后顺序
     */

    public static void basicUse() throws Exception { //基本使用
        Future<String> future = calculateAsync(new CompletableFuture<>());
        String result = future.get(); //阻塞获取结果
        System.out.println("输出的结果：" + result + ",时间戳：" + System.currentTimeMillis() / 1000);

        int a = 1;
        System.out.println("另外的任务：" + a + ",时间戳：" + System.currentTimeMillis() / 1000); //从运行的结果来看，线程会阻塞，要等前面运行好，当前语句才会执行
    }

    public static void useRunAsync() throws Exception { //使用runAsync
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

    public static void useAsyncDeal(boolean expect) throws Exception { //模拟异步回调场景
        System.out.println("进入方法开始时间：" + System.currentTimeMillis() / 1000);
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (!expect) {
                throw new IllegalArgumentException();
            }
            return "test_result";
        });
        future.thenAccept(result -> { //异步任务正常完成时回调
            System.out.println("成功_输出的结果3：" + ",时间戳：" + System.currentTimeMillis() / 1000);
        });

        future.exceptionally(e -> { //异步任务发生异常时回调
            System.out.println("异常_输出的结果3：" + ",时间戳：" + System.currentTimeMillis() / 1000);
            e.printStackTrace();
            return null;
        });

        System.out.println("另外的任务3：" + ",时间戳：" + System.currentTimeMillis() / 1000); //从运行结果上来看，按异步回调方式，是不会阻塞，影响当前业务的

        System.in.read(); //注意：主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
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
     *
     * https://www.liaoxuefeng.com/wiki/1252599548343744/1306581182447650  使用CompletableFuture
     * 1）使用Future获得异步执行结果时，要么调用阻塞方法get()，要么轮询看isDone()是否为true，这两种方法都不是很好，因为主线程也会被迫等待。
     *    从Java 8开始引入了CompletableFuture，它针对Future做了改进，可以传入回调对象，当异步任务完成或者发生异常时，自动调用回调对象的回调方法。
     * 2） CompletableFuture更强大的功能是，多个CompletableFuture可以串行执行（如：后面的CompletableFuture使用的实例创建）
     * 3）除了串行执行外，多个CompletableFuture还可以并行执行（使用CompletableFuture.anyOf()方法处理）
     *
     */
}
