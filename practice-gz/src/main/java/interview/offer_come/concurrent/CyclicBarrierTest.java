package interview.offer_come.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class CyclicBarrierTest { //@MsY-Done

    /**
     * 知识点：回环栅栏
     * （参见：com.csy.interview.written_exam.thread.CyclicBarrierTest）
     */

    public static void main(String[] args) {
        int N = 4;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N, new Thread(() -> {
            System.out.println("CyclicBarrier Test!");
        }));

        for (int i = 0; i < N; i++) {
            new BusinessThread(cyclicBarrier).start();
        }

        /**
         * 输出结果：
         * 线程执行前准备工作完成，等待其他线程准备工作完成
         * 线程执行前准备工作完成，等待其他线程准备工作完成
         * 线程执行前准备工作完成，等待其他线程准备工作完成
         * 线程执行前准备工作完成，等待其他线程准备工作完成
         * CyclicBarrier Test!
         *
         * 结果分析：
         * 1）创建了计数为4的CyclicBarrier，当计数减为0时，执行对应的任务以及唤醒关联的任务
         */
    }

    static class BusinessThread extends Thread{
        private CyclicBarrier cyclicBarrier;

        public BusinessThread(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
                System.out.println("线程执行前准备工作完成，等待其他线程准备工作完成");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
