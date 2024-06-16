package interview.offer_come.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class CountDownLatchTest { //@MsY-Doing

    /**
     * 知识点：闭锁
     * （参见com.csy.interview.written_exam.thread.CountDownLatchTest中描述）
     */
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(2);
        new Thread() {
            public void run() {
                try {
                    System.out.println("子线程1" + "正在执行");
                    Thread.sleep(3000);
                    System.out.println("子线程1" + "执行完毕");
                    latch.countDown();
                } catch (Exception e) {
                }
            }
        }.start();

        new Thread() {
            public void run() {
                try {
                    System.out.println("子线程2" + "正在执行");
                    Thread.sleep(3000);
                    System.out.println("子线程2" + "执行完毕");
                    latch.countDown();
                } catch (Exception e) {

                }
            }
        }.start();

        try {
            System.out.println("等待两个子线程执行完毕...");
            latch.await();
            System.out.println("两个子线程已经执行完毕，继续执行主线程");
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 输出结果：
         * 子线程1正在执行
         * 等待两个子线程执行完毕...
         * 子线程2正在执行
         * 子线程1执行完毕
         * 子线程2执行完毕
         * 两个子线程已经执行完毕，继续执行主线程
         *
         * 结果分析：
         * 1）设置了计数为2的CountDown，通过调用countDown()方法来减计数，计数减为0时，释放所有等待的线程
         * 2）两个线程执行体中都调用了countDown()方法，都会将计数减1，减了2次后为0，主线程就可以执行了
         */
    }
}
