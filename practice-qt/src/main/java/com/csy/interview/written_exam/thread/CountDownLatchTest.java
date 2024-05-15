package com.csy.interview.written_exam.thread;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @author chensy
 * @date 2023/9/17
 */
public class CountDownLatchTest { //@MsY-Doing

    /**
     * 知识点：CountDownLatch_闭锁
     * （CountDown：倒数，Latch：门栓）
     *
     * 知识点概要：
     * 1）A synchronization aid（帮组） that allows one or more threads to wait until
     *    a set of operations being performed in other threads completes.
     *
     * 2）CountDownLatch#CountDownLatch(int)：
     *    Constructs a CountDownLatch initialized with the given count.
     *
     * 3）CountDownLatch#countDown()：
     *    Decrements（递减） the count of the latch, releasing all waiting threads if the count reaches zero.
     *
     * 4）CountDownLatch#await()：
     *   Causes the current thread to wait until the latch has counted down to
     *    zero, unless the thread is interrupted.
     *
     * 5）CountDownLatch：经常被称为闭锁，它能够使指定线程等待计数线程完成各自工作后再执行。（可以实现类似计数器的功能）
     *                   Latch：门栓，很形象的描述它的工作模式，用户需要一道道解开门栓，才能打开门
     *
     * 6）CountDownLatch提供了对一组线程任务进行约束的能力，可以在任务中灵活根据条件来调用latch#countDown()方法，从而决定是否中断CountDownLatch#await造成的阻塞。
     *    CountDownLatch只能一次性使用，它的计数不会在回归原处。
     *
     *
     * 问题点答疑：
     * 1）CountDownLatch是怎样计数的？在计数值未达到0时，是怎么让线程阻塞的？
     *
     */

    /**
     * 场景1：基本使用
     */
    @Test
    public void test_basic() {
        CountDownLatch latch = new CountDownLatch(2); //定义计数为2的闭锁

        new Thread(() -> {
            System.out.println("第一个线程开始工作");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第一个线程工作结束");
            latch.countDown(); //计数减1
        }).start();

        new Thread(() -> {
            System.out.println("第二个线程开始工作");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第二个线程工作结束");
            latch.countDown();
        }).start();

        try {
            latch.await(); //使当前线程阻塞，直到闭锁的计数减为0（若计数没减到0，此处会一直阻塞，除非被中断）
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("所有任务都已经完成");

        /**
         * 输出结果：
         * 第一个线程开始工作
         * 第二个线程开始工作
         * 第一个线程工作结束
         * 第二个线程工作结束
         * 所有任务都已经完成
         *
         * 结果分析：
         * 1）创建了计数为2的闭锁，启动线程后，在线程时调用latch.countDown()，会将计数减1
         *    而调用latch.await()时，会阻塞当前线程，直到计数为0
         */
    }
}
