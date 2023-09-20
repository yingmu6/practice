package com.csy.interview.no2;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author chensy
 * @date 2023/9/17
 */
public class CyclicBarrierTest {

    /**
     * CyclicBarrier_回环栅栏测试
     * （Cyclic：循环的，Barrier：栅栏）
     *
     * 1）The barrier is called cyclic because it can be re-used after the waiting threads are released.
     *
     * 2）通过debug看源码，在每次调用await()时，会将计数减1，若计数不为0，则线程会阻塞等待。否则，通过condition#signAll唤醒所有线程，继续后续的任务
     */

    /**
     * 场景1：基本使用
     */
    @Test
    public void test_basic() throws IOException, InterruptedException {

        CyclicBarrier barrier = new CyclicBarrier(2, new Thread(() -> System.out.println("Hello CyclicBarrier!"))); //在设置计数的时候，可以执行计数完成时，执行的任务

        new Thread(() -> {
            System.out.println("第一个线程开始工作");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                System.out.println("第一个线程等待其它线程完成工作");
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("第一个线程继续工作，timeStamp=" + System.currentTimeMillis());
        }).start();

        new Thread(() -> {
            System.out.println("第二个线程开始工作");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                System.out.println("第二个线程等待其它线程完成工作");
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
               e.printStackTrace();
            }
            System.out.println("第二个线程继续工作，timeStamp=" + System.currentTimeMillis());
        }).start();

        Thread.sleep(10000);

        // 再次使用（重置计数器）
        barrier.reset();
        new Thread(() -> {
            System.out.println("第三个线程开始工作，timeStamp=" + System.currentTimeMillis());
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            System.out.println("第三个线程结束工作，timeStamp=" + System.currentTimeMillis());
        }).start();

        new Thread(() -> {
            System.out.println("第四个线程开始工作，timeStamp="+ System.currentTimeMillis());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            System.out.println("第四个线程结束工作，timeStamp=" + System.currentTimeMillis());
        }).start();

        System.in.read();

        /**
         * 输出结果：
         *
         * 第一个线程开始工作
         * 第二个线程开始工作
         * 第一个线程等待其它线程完成工作
         * 第二个线程等待其它线程完成工作
         * Hello CyclicBarrier!
         * 第一个线程继续工作，timeStamp=1695023301051
         * 第二个线程继续工作，timeStamp=1695023301051
         *
         * 第三个线程开始工作，timeStamp=1695023308050
         * 第四个线程开始工作，timeStamp=1695023308050
         * Hello CyclicBarrier!
         * 第四个线程结束工作，timeStamp=1695023311051
         * 第三个线程结束工作，timeStamp=1695023311051
         *
         * 结果分析：
         * 1）通过计数器来约束线程，创建了计数为2的CyclicBarrier回环栅栏，线程中通过调用barrier.await阻塞线程，直到其它线程调用
         *   barrier.await将计数器减为0，然后执行所有线程的剩余部分，从时间戳来看，毫秒是相同的，差值在微秒级，可以认为是并发执行的。
         *
         * 2）在创建CyclicBarrier时，可以指定任务，在完成计数时执行任务
         *
         * 3）使用reset()方法，可以将计数器恢复到原来计数（debug调试源码可知，在创建对象时，会设置count、parties值，两个相同，当使用完barrier计数是，
         *    可通过reset()方法，将parties赋值给count）
         */
    }
}
