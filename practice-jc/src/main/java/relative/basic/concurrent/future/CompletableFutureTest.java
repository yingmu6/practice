package relative.basic.concurrent.future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author chenSy
 * @Date 2023/01/16 22:28
 * @Description
 */
public class CompletableFutureTest {

    /**
     * CompletableFuture_概述
     *
     * 1）Future是比较难知道任务已经完成的，需要阻塞或轮询获取结果
     * Asynchronous computation is difficult to reason about
     * but it did not have any methods to combine these computations or handle possible errors.
     * （Future没有任何方法用来绑定完成或异常操作）
     *
     * 2）First, the CompletableFuture class implements the
     * Future interface so that we can use it as a Future implementation but with additional completion logic.
     * （CompletableFuture实现了Future，可以作为Future的额外功能）
     *
     * 3）使用CompletableFuture的优点：
     *   a）使用Future获得异步执行结果时，要么调用阻塞方法get()，要么轮询看isDone()是否为true，这两种方法都不是很好，因为主线程也会被迫等待。
     *      从Java 8开始引入了CompletableFuture，它针对Future做了改进，可以传入回调对象，当异步任务完成或者发生异常时，自动调用回调对象的回调方法。
     *   b） CompletableFuture更强大的功能是，多个CompletableFuture可以串行执行（如：后面的CompletableFuture使用的实例创建）
     *   c）除了串行执行外，多个CompletableFuture还可以并行执行（使用CompletableFuture.anyOf()方法处理）
     *
     * 参考链接：
     * a）https://www.baeldung.com/java-completablefuture  英文版介绍
     * b）https://www.liaoxuefeng.com/wiki/1252599548343744/1306581182447650  使用CompletableFuture
     */

    /**
     * 场景1：使用future的get()方法阻塞获取
     */
    @Test
    public void test_future_get() throws Exception {
        System.out.println("当前的时间戳：" + System.currentTimeMillis() / 1000);
        Future<String> future = calculateAsync(new CompletableFuture<>());
        String result = future.get(); //阻塞获取结果
        System.out.println("输出的结果：" + result + ",时间戳：" + System.currentTimeMillis() / 1000);

        int a = 1;
        System.out.println("另外的任务：" + a + ",时间戳：" + System.currentTimeMillis() / 1000); //从运行的结果来看，调用future.get()线程会阻塞
    }

    /**
     * 场景2：使用CompletableFuture的get()阻塞获取
     */
    @Test
    public void useRunAsync() throws Exception {
        System.out.println("当前的时间戳：" + System.currentTimeMillis() / 1000);
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

    /**
     * 场景3：使用异步方式
     */
    @Test
    public void useAsyncDeal() throws Exception {
        System.out.println("进入方法开始时间：" + System.currentTimeMillis() / 1000);
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
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

        /**
         * 输出结果：
         * 进入方法开始时间：1688309136
         * 另外的任务3：,时间戳：1688309136
         * 成功_输出的结果3：,时间戳：1688309141
         *
         * 结果分析：
         * 从运行结果来看，使用CompletableFuture的回调方法thenAccept（成功时回调）、exceptionally（异常时回调），就不会阻塞
         * 当前线程的其它业务执行了，如此处的"另外的任务3..."，不会受CompletableFuture.supplyAsync(...)任务影响了
         */
    }

    public static Future<String> calculateAsync(CompletableFuture<String> completableFuture) throws InterruptedException {

        Executors.newCachedThreadPool().submit(() -> { //使用线程池异步执行
            Thread.sleep(5000);
            completableFuture.complete("Hello");
            return null;
        });

        return completableFuture;
    }
}
