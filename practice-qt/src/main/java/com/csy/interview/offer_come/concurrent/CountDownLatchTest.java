package com.csy.interview.offer_come.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(2);
        new Thread() {
            public void run() {
                try {
                    System.out.println("子线程1" + "正在执行");
                    Thread.sleep(3000);
                    System.out.println("子线程1" + "执行完毕");
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

    }
}
