package com.csy.interview.offer_come.concurrent.lock;

import org.junit.Test;

import java.io.IOException;

/**
 * @author chensy
 * @date 2024/1/6
 */
public class SynchronizedV2Test {

    /**
     * 场景1：synchronized修饰普通方法，锁住的是当前实例对象（作用于同一个对象）
     */
    @Test
    public void testGeneralMethodV1() throws IOException {
        SynchronizedV2Test demoV1 = new SynchronizedV2Test();
        new Thread(new Runnable() {
            @Override
            public void run() {
                demoV1.generalMethod1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                demoV1.generalMethod2();
            }
        }).start();

        System.in.read();

        /**
         * 输出结果：（顺序执行）
         * generalMethod1 execute 1 time
         * generalMethod1 execute 2 time
         * generalMethod2 execute 1 time
         * generalMethod2 execute 2 time
         *
         * 结果分析：
         * 1）因为synchronized修饰的是普通方法，所以锁住的是当前对象即demoV1，虽然启动了两个线程，但第二个线程要做等待，直到第一个线程处理完释放锁才能进行
         * 2）第一个线程执行完，释放了锁，第二个线程才能进行处理
         */
    }

    /**
     * 场景2：synchronized修饰普通方法，锁住的是当前实例对象（作用于不同的对象）
     */
    @Test
    public void testGeneralMethod() throws IOException {
        SynchronizedV2Test demoV1 = new SynchronizedV2Test();
        SynchronizedV2Test demoV2 = new SynchronizedV2Test(); //使用的对象不同
        new Thread(new Runnable() {
            @Override
            public void run() {
                demoV1.generalMethod1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                demoV2.generalMethod2();
            }
        }).start();

        System.in.read();

        /**
         * 结果输出：（并发执行）
         * generalMethod1 execute 1 time
         * generalMethod2 execute 1 time
         * generalMethod1 execute 2 time
         * generalMethod2 execute 2 time
         *
         * 结果分析：
         * 1）synchronized作用在普通方法上，锁住的是当前调用方法的对象。因为调用的对象demoV1、demoV2不一样，用的不是同一把锁，所以就可以并发执行了
         */
    }

    /**
     * 场景3：synchronized修饰静态方法时，锁的是类的Class对象
     */
    @Test
    public void testStaticMethod() throws IOException {
        SynchronizedV2Test demoV1 = new SynchronizedV2Test();
        SynchronizedV2Test demoV2 = new SynchronizedV2Test(); //使用的对象不同
        new Thread(new Runnable() {
            @Override
            public void run() {
                demoV1.staticMethod1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                demoV2.staticMethod2();
            }
        }).start();

        System.in.read();

        /**
         * 结果输出：（顺序执行）
         * staticMethod1 execute 1 time
         * staticMethod1 execute 2 time
         * staticMethod2 execute 1 time
         * staticMethod2 execute 2 time
         *
         * 结果分析：
         * 1）从输出结果是顺序执行线程的，因为synchronized修饰static方法时，锁住的是类的class对象，所以不管是几个对象去操作
         *    都是锁住同一个Class对象，所以使用同一把锁的。
         */
    }

    /**
     * 场景4：synchronized修饰方法块时，锁住的是括号里面配置的对象
     */
    @Test
    public void testBlock() throws Exception {
        SynchronizedV2Test demo = new SynchronizedV2Test();
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.blockMethod1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.blockMethod2();
            }
        }).start();

        System.in.read();

        /**
         * 输出结果：
         * blockMethod1 execute
         * blockMethod1 execute
         * blockMethod2 execute
         * blockMethod2 execute
         *
         * 结果分析：
         * 1）由于两个方法都需要获取名为lockA的锁，所以线程会等待，直到一个线程完成后释放锁，另一个线程才能执行
         */
    }

    public synchronized void generalMethod1() { //synchronized修饰普通方法时，锁住的是当前调用的对象
        try {
            for (int i = 1; i < 3; i++) {
                System.out.println("generalMethod1 execute " + i + " time");
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void generalMethod2() {
        try {
            for (int i = 1; i < 3; i++) {
                System.out.println("generalMethod2 execute " + i + " time");
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void staticMethod1() { //synchronized修饰静态方法时，锁住的是类的Class对象
        try {
            for (int i = 1; i < 3; i++) {
                System.out.println("staticMethod1 execute " + i + " time");
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void staticMethod2() {
        try {
            for (int i = 1; i < 3; i++) {
                System.out.println("staticMethod2 execute " + i + " time");
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    String lockA = "lockA";
//    String lockB = "lockA"; 注意：如果字符串值相等，为同一个字符串对象，此处使用lockA和lockB都是对同一个对象加锁。
    public void blockMethod1() {
        try {
            synchronized (lockA) {
                for (int i = 1; i < 3; i++) {
                    System.out.println("blockMethod1 execute");
                    Thread.sleep(3000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void blockMethod2() {
        try {
            synchronized (lockA) {
                for (int i = 1; i < 3; i++) {
                    System.out.println("blockMethod2 execute");
                    Thread.sleep(3000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
