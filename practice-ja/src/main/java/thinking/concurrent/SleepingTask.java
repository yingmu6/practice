package thinking.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author chensy
 * @date 2024/4/13
 */
public class SleepingTask extends LiftOff { //@TkY-Doing

    /**
     * 知识点：休眠
     *
     * 知识点概括：
     * 1）
     */

    public void run() { //重写了LiftOff中的run方法，线程执行时进入当前方法
        try {
            while (countDown-- > 0) {
                System.out.println(status());
                TimeUnit.MILLISECONDS.sleep(100); //让当前线程睡眠100毫秒
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted");
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new SleepingTask());
        }
        exec.shutdown();

        /**
         * 输出结果：（输出顺序可能会变）
         *
         * #1(9)，
         * #3(9)，
         * #4(9)，
         * #2(9)，
         * #0(9)，
         * ........
         * #0(1)，
         * #2(1)，
         * #3(1)，
         * #1(Liftoff!)，
         * #0(Liftoff!)，
         * #4(Liftoff!)，
         * #3(Liftoff!)，
         * #2(Liftoff!)，
         *
         * 结果分析：
         */
    }
}
