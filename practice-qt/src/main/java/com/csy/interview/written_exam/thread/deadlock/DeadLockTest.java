package com.csy.interview.written_exam.thread.deadlock;

import org.junit.Test;

import java.io.IOException;

/**
 * @author chensy
 * @date 2023/9/24
 */
public class DeadLockTest {

    /**
     * 死锁_测试
     */

    public static Object obj1 = new Object();
    public static Object obj2 = new Object();

    /**
     * 场景1：基本使用
     */
    @Test
    public void test_basic_deadLock() throws IOException {
        Thread t1 = new Thread(new T1());
        Thread t2 = new Thread(new T2());
        t1.start();
        t2.start();

        System.in.read();

        /**
         * 输出结果：
         * Thread t1 is started
         * Thread t1 lock obj1
         * Thread t2 is started
         * Thread t2 lock obj2
         *
         * 结果分析：
         */
    }

    /**
     * 场景2：synchronized修饰方法块时，产生死锁
     */
    @Test
    public void test_deadLock_V2() throws Exception {
        DeadLockTest deadLockTest = new DeadLockTest();
        new Thread(() -> deadLockTest.blockMethod1()).start();
        new Thread(() -> deadLockTest.blockMethod2()).start();

        System.in.read();

        /**
         * 输出结果：
         * method 1 execute
         * method 2 execute
         *
         * 结果分析：
         *
         * 总结概括：
         * 1）如何判断是否产生了死锁？
         */
    }

    String lockA = "lockA";
    String lockB = "lockB";
    public void blockMethod1() {
        try {
            synchronized (lockA) {
                for (int i = 1; i < 3; i++) {
                    System.out.println("method 1 execute");
                    Thread.sleep(3000);
                    synchronized (lockB) {}
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void blockMethod2() {
        try {
            synchronized (lockB) {
                for (int i = 1; i < 3; i++) {
                    System.out.println("method 2 execute");
                    Thread.sleep(3000);
                    synchronized (lockA){}
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
