package thinking.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chensy
 * @date 2024/4/13
 */
public class SimplePriorities implements Runnable { //@TkY-Doing

    /**
     * 知识点：线程优先级
     *
     * 知识点概括：
     * 1）
     */
    private int countDown = 5;

    private volatile double d;

    private int priority;

    public SimplePriorities(int priority) {
        this.priority = priority;
    }

    public String toString() {
        return Thread.currentThread() + "：" + countDown;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(priority);
        while (true) {
            for (int i = 0; i < 10000; i++) {
                d += (Math.PI + Math.E) / (double) i;
                if (i % 1000 == 0) {
                    Thread.yield();
                }
            }

            System.out.println(this);
            if (--countDown == 0) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
        }
        exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));
        exec.shutdown();

        /**
         * 输出结果：（每次运行结果，可能会变）
         * Thread[pool-1-thread-1,1,main]：5
         * Thread[pool-1-thread-2,1,main]：5
         * Thread[pool-1-thread-3,1,main]：5
         * Thread[pool-1-thread-4,1,main]：5
         * Thread[pool-1-thread-5,1,main]：5
         * Thread[pool-1-thread-6,10,main]：5
         * Thread[pool-1-thread-1,1,main]：4
         * Thread[pool-1-thread-2,1,main]：4
         * Thread[pool-1-thread-3,1,main]：4
         * Thread[pool-1-thread-4,1,main]：4
         * Thread[pool-1-thread-5,1,main]：4
         * Thread[pool-1-thread-6,10,main]：4
         * Thread[pool-1-thread-1,1,main]：3
         * Thread[pool-1-thread-2,1,main]：3
         * Thread[pool-1-thread-3,1,main]：3
         * Thread[pool-1-thread-4,1,main]：3
         * Thread[pool-1-thread-5,1,main]：3
         * Thread[pool-1-thread-6,10,main]：3
         * Thread[pool-1-thread-1,1,main]：2
         * Thread[pool-1-thread-3,1,main]：2
         * Thread[pool-1-thread-2,1,main]：2
         * Thread[pool-1-thread-4,1,main]：2
         * Thread[pool-1-thread-6,10,main]：2
         * Thread[pool-1-thread-1,1,main]：1
         * Thread[pool-1-thread-3,1,main]：1
         * Thread[pool-1-thread-2,1,main]：1
         * Thread[pool-1-thread-4,1,main]：1
         * Thread[pool-1-thread-6,10,main]：1
         * Thread[pool-1-thread-5,1,main]：2
         * Thread[pool-1-thread-5,1,main]：1
         *
         * 结果分析：
         * 1）
         */
    }
}
