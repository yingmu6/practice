package relative.basic.concurrent.future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
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
     * c）https://mikechen.cc/15629.html CompletableFuture原理与用法详解（8大使用场景）
     * d）https://tech.meituan.com/2022/05/12/principles-and-practices-of-completablefuture.html CompletableFuture原理与实践-外卖商家端API的异步化（写的比较好）
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
        System.out.println("另外的任务：" + a + ",时间戳：" + System.currentTimeMillis() / 1000); //从运行的结果来看，调用future.get()线程会阻塞主线程中其它任务
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

        System.out.println("输出的结果2：" + future.get() + ",时间戳：" + System.currentTimeMillis() / 1000); //也是阻塞着等结果（与Future.get()效果类似）

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
            System.out.println("成功_输出的结果3：" + ",时间戳：" + System.currentTimeMillis() / 1000 + ",结果值：" + result);
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

    /**
     * 场景4：线程串行化
     * （当一个线程依赖另一个线程时，可以使用thenApply方法来把这两个线程串行化）
     */
    @Test
    public void test_serialization() throws ExecutionException, InterruptedException {
        System.out.println("主线程开始");
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "第一个线程！")
                .thenApply(x -> x + "第二个线程！") //1）
                .thenApply(x -> x + "第三个线程！"); //2）

        String result = future.get();
        System.out.println("主线程结束，子线程的结果为：\n" + result);

        /**
         * 输出结果：
         * 主线程开始
         * 主线程结束，子线程的结果为：
         * 第一个线程！第二个线程！第三个线程！
         *
         * 结果分析：
         * 代码1）、2）线程依次执行，并且前一个线程的结果，作为后一个线程的输入条件
         */
    }

    /**
     * 场景5：thenApply()、thenAccept()联合使用
     * 1）thenApply()用于多个线程串行化
     * 2）thenAccept()用于处理异步结果
     */
    @Test
    public void test_combine() {
        CompletableFuture.supplyAsync(() -> "How")
                .thenApply(x -> x + " are you ")  // 1)
                .thenAccept(result -> System.out.println(result)); // 2）

        /**
         * 输出结果：
         * How are you
         *
         * 结果分析：
         * 代码1）是为了将多个线程串行化，代码2）是否了处理异步计算的结果
         */
    }

    private static String tempStr = "";
    /**
     * 场景6：CompletableFuture的创建方式
     */
    @Test
    public void test_create() {

        /**
         * 主要提供了4个静态方法，来创建CompletableFuture
         *
         * 1）public static CompletableFuture<Void> runAsync(Runnable runnable)
         * 2）public static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor)
         * 3）public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
         * 4）public static <U > CompletableFuture < U > supplyAsync(Supplier < U > supplier, Executor executor)
         *
         * supplyAsync与runAsync的区别主要在于，supplyAsync会返回结果，而runAsync不会返回结果
         */

        // 方式一：使用runAsync（未指定线程池，就使用默认线程池）
        CompletableFuture.runAsync(() -> tempStr = "How") //因为runAsync不会返回值，所以借助外部变量tempStr处理
                .thenApply(x -> {
                    System.out.println("runAsync中的x的值：" + x); //会输出："x的值：null"
                    return tempStr = tempStr + " are you";
                })
                .thenAccept(x -> System.out.println("runAsync中的结果：" + tempStr));

        // 方式二：使用supplyAsync
        CompletableFuture.supplyAsync(() -> "How") //因为supplyAsync会返回值，所以后面的线程可以使用前一个线程的结果值
                .thenApply(x -> {
                    System.out.println("supplyAsync中的x的值：" + x); //会输出："x的值：How"
                    return x + " are you";
                })
                .thenAccept(x -> System.out.println("supplyAsync中的结果：" + x));
    }
}
