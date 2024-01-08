package com.csy.interview.no2;

import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author chensy
 * @date 2023/9/7
 */
public class ReentrantLockTest {

    /**
     * ReentrantLock可重入锁_测试
     * 1）重入锁：又叫做递归锁，是指在同一个线程中，外部方法获取锁之后，内部方法依然可以获取到锁。【可以简单理解为：同一个线程中可以多次获取到锁】
     *   （如果锁不具备重入性，那么当同一个线程两次获取到锁时会发生死锁）
     */

    private Lock lock = new ReentrantLock();

     /**
     * 场景1：可重入锁基本使用
     */
    @Test
    public void test_basic_use() {
        method1();

        /**
         * 输出结果：
         * method1 is called
         * method2 is called
         *
         * 结果分析：
         * 1）调用method1()时，已经获取到锁，然后调用method2()时，又再次获取到锁，继续正常执行
         */
    }

    private void method1() {
        lock.lock();
        System.out.println("method1 is called");
        method2();
        lock.unlock();
    }

    private void method2() {
        lock.lock();
        System.out.println("method2 is called");
        lock.unlock();
    }


    /**
     * 场景2：ReentrantLock锁的特性测试
     */
    @Test
    public void test_lock_characteristic_v1() throws IOException {
        Demo demo = new Demo();

        Thread1 t1 = new Thread1(demo); //两个线程对同一个对象进行处理，即持有相同的对象监控器
        Thread2 t2 = new Thread2(demo);
        t1.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }
        t2.start();
        System.in.read();

        /**
         * 输出结果：
         * method1 is called，timestamp=Thu Sep 07 10:56:37 CST 2023
         * method1 is called，timestamp=Thu Sep 07 10:56:42 CST 2023
         * method2 is called，timestamp=Thu Sep 07 10:56:42 CST 2023
         * method2 is called，timestamp=Thu Sep 07 10:56:42 CST 2023
         *
         * 结果分析：
         * 1）线程1执行了method1后，sleep 5秒，因为处理的同一个对象demo，所以线程1还未执行完时，线程2需要等待
         * 2）线程1执行完方法后，线程2才能继续执行。
         */
    }

    @Test
    public void test_lock_characteristic_v2() throws IOException {
        Demo demo1 = new Demo();
        Demo demo2 = new Demo(); //非同一个对象

        Thread1 t1 = new Thread1(demo1);
        Thread2 t2 = new Thread2(demo2);
        t1.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }
        t2.start();
        System.in.read();

        /**
         * 输出结果：
         * method1 is called，timestamp=Thu Sep 07 11:20:47 CST 2023
         * method2 is called，timestamp=Thu Sep 07 11:20:49 CST 2023
         * method2 is called，timestamp=Thu Sep 07 11:20:49 CST 2023
         * method1 is called，timestamp=Thu Sep 07 11:20:52 CST 2023
         *
         * 结果分析：
         * 1）因为两个线程处理的不是同一个对象，不会相互影响，所以就会交替执行。
         *  （所以多线程处理时，要判断是否处理的是共享资源）
         */
    }

    /**
     * 场景3：ReentrantReadWriteLock使用（读锁使用）
     * 1）ReentrantLock是排它锁，即在同一时刻仅有一个线程能进行访问。虽然能实现多线程同步，但一些场景下性能不是很好，比如少量写大量读场景。
     * 2）ReentrantReadWriteLock可重入的读写锁，把锁进一步分为读锁和写锁。读锁可以被多个线程同时持有，而写锁是独占的。
     */
    @Test
    public void test_ReentrantReadWriteLock_ReadLock() throws IOException {
        ReadWriteDemo demo = new ReadWriteDemo();
        ReadDemoThread t1 = new ReadDemoThread(demo);
        ReadDemoThread t2 = new ReadDemoThread(demo);
        t1.start();
        t2.start();

        System.in.read();
        /**
         * 输出结果：
         * Thread-0正在进行读操作
         * Thread-0正在进行读操作
         * Thread-0正在进行读操作
         * ....
         * Thread-1正在进行读操作
         * Thread-1正在进行读操作
         * Thread-1正在进行读操作
         * ....
         * Thread-0正在进行读操作
         * Thread-0正在进行读操作
         * ....
         * Thread-0读操作完毕
         * Thread-1读操作完毕
         *
         * 结果分析：
         * 1）从运行结果上来看，两个线程时交替执行的，也就是可以获取到读锁进行读操作
         */
    }

    /**
     * 场景4：ReentrantReadWriteLock使用（写锁使用）
     */
    @Test
    public void test_ReentrantReadWriteLock_WriteLock() throws IOException {
        ReadWriteDemo demo = new ReadWriteDemo();
        WriteDemoThread t1 = new WriteDemoThread(demo);
        WriteDemoThread t2 = new WriteDemoThread(demo);
        t1.start();
        t2.start();

        System.in.read();

        /**
         * 输出结果：
         * Thread-0正在进行写操作
         * Thread-0正在进行写操作
         * Thread-0正在进行写操作
         * .....
         * Thread-0写操作完毕
         *
         * 结果分析：
         * 1）使用写锁时，没有交替执行，一直执行完一个线程
         *
         * 问题点答疑：
         * 1）运行中，为什么只看Thread-0线程运行，Thread-1线程没有看到运行？
         */
    }

    /**
     * 场景5：ReentrantLock基本使用
     */
    @Test
    public void test_basic_use_V2() throws Exception {
        ReenterLockDemo lockDemo = new ReenterLockDemo();
        Thread t1 = new Thread(lockDemo);
        t1.start();
        t1.join();
        System.out.println(lockDemo.getI());

        /**
         * 输出结果：
         * 10
         *
         * 结果分析：
         * 由于Lock的类型为ReentrantLock，属于可重入锁，所以一个线程可以多次获取锁
         *
         * 问题点答疑：
         * 1）哪些锁是不可重入的？多次用锁，会报错吗？
         */
    }

    /**
     * 场景6：ReentrantLock高级使用：避免死锁（响应中断、可轮训锁、定时锁）
     */
    @Test
    public void test_advanced_used() {

    }

    public class InterruptiblyLock {
        public ReentrantLock lock1;
    }

    public class ReenterLockDemo implements Runnable {
        public ReentrantLock lock = new ReentrantLock();
        public int i = 0;

        @Override
        public void run() {
            for (int j = 0; j < 10; j++) {
                lock.lock();
                lock.lock(); //可重复加锁

                try {
                    i++;
                } finally {
                    lock.unlock();
                    lock.unlock();
                }
            }
        }

        public int getI() {
            return i;
        }
    }

    class ReadWriteDemo {
        private ReentrantReadWriteLock rw = new ReentrantReadWriteLock();

        public void read() {
            rw.readLock().lock();

            int i = 0;
            while (i++ < 100) {
                System.out.println(Thread.currentThread().getName() + "正在进行读操作");
            }
            System.out.println(Thread.currentThread().getName() + "读操作完毕");
        }

        public void write() {
            rw.writeLock().lock();

            int i = 0;
            while (i++ < 100) {
                System.out.println(Thread.currentThread().getName() + "正在进行写操作");
            }
            System.out.println(Thread.currentThread().getName() + "写操作完毕");
        }
    }

    class ReadDemoThread extends Thread {
        private ReadWriteDemo demo;

        public ReadDemoThread(ReadWriteDemo demo) {
            this.demo = demo;
        }

        public void run() {
            demo.read();
        }
    }

    class WriteDemoThread extends Thread {
        private ReadWriteDemo demo;

        public WriteDemoThread(ReadWriteDemo demo) {
            this.demo = demo;
        }

        public void run() {
            demo.write();
        }
    }

    class Demo {
        private Lock lock = new ReentrantLock();

        public void method1() {
            try {
                lock.lock();
                System.out.println("method1 is called，timestamp=" + new Date());
                Thread.sleep(5000); //线程睡眠5秒
                System.out.println("method1 is called，timestamp=" + new Date());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void method2() {
            lock.lock();
            System.out.println("method2 is called，timestamp=" + new Date());
            System.out.println("method2 is called，timestamp=" + new Date());
            lock.unlock();
        }
    }

    class Thread1 extends Thread {
        private Demo td;

        public Thread1(Demo td) {
            this.td = td;
        }

        public void run() {
            td.method1();
        }
    }

    class Thread2 extends Thread {
        private Demo td;

        public Thread2(Demo td) {
            this.td = td;
        }

        public void run() {
            td.method2();
        }
    }
}
