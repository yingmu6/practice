package thinking.concurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class ThreadLocalVariableHolder { //@JaY-Doing

    /**
     * 知识点：线程本地存储
     *
     * 知识点概括：
     * 1）
     *
     * 问题点答疑：
     * 1）ThreadLocal的底层原理是什么？怎样与当前线程关联起来的？内部的缓存是怎样的？
     */

    static class Accessor implements Runnable {
        private final int id;
        public Accessor(int idn) {
            id = idn;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                ThreadLocalVariableHolder.increment();
                System.out.println(this);
                Thread.yield();
            }
        }

        public String toString() {
            return "#" + id + "：" + ThreadLocalVariableHolder.get();
        }
    }

    private static ThreadLocal<Integer> value =  //虽然是static变量，但各个线程的ThreadLocal值互不影响
            new ThreadLocal<Integer>() {
                private Random rand = new Random(47);
                protected synchronized Integer initialValue() {
                    return rand.nextInt(10000);
                }
            };

    public static void increment() {
        value.set(value.get() + 1);
    }

    public static int get() {
        return value.get();
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new Accessor(i)); //提交任务到线程池
        }

        TimeUnit.SECONDS.sleep(3);
        exec.shutdown();

        /**
         * 输出结果：
         * 7849
         * #2：211905
         * #2：211906
         * #3：217850
         * #3：217851
         * #3：217852
         * #4：212640
         * #4：212641
         * #1：211832
         * #1：211833
         * #0：219402
         * #0：219403
         * #0：219404
         * ......
         *
         *
         * 结果分析：
         * 1）ThreadLocal类型value虽然是static变量，但是每个线程维护的值不一样，不会相互影响，
         *    从运行结果看，每个线程都是基于自己维护的值，进行处理
         */
    }
}
