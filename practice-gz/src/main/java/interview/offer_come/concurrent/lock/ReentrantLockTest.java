package interview.offer_come.concurrent.lock;

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
public class ReentrantLockTest { //@MsY_Doing

    /**
     * 知识点：ReentrantLock可重入锁
     * 1）重入锁：又叫做递归锁，是指在同一个线程中，外部方法获取锁之后，内部方法依然可以获取到锁。【可以简单理解为：同一个线程可以用同一把锁进行多次加锁和解锁】
     * （如果锁不具备重入性，那么当同一个线程两次获取到锁时会发生死锁）
     *
     * 2）FairSync和NonfairSync的主要区别在于：NonfairSync一上来就会抢锁，如果抢锁成功就设置当前线程为持有独占锁的线程，抢锁失败则进入AQS的模板方法acquire(1)
     * 而FairSync不会一上来就抢锁，而是先判断队列中是否有其他线程在等待锁，没有再抢锁。
     *
     * 3）ReentrantLock默认非公平锁是为了减少线程间的切换，从而提高效率。
     *
     * 问题点答疑：
     * 1）ReentrantLock为什么具有可重入性？Lock的本质是什么，CAS吗？
     *    解答：因为多一个线程多次lock时，即state不为0时，会尝试进行加锁，而尝试加锁是会判断锁的持有线程是不是同一个，只要是同一个线程，即加锁成功，
     *         并且把state加1。ReentrantLock的本质就是借助了AQS的volatile变量state实现的，也就是CAS
     *
     * 2）ReentrantLock中的FailSync和NonfairSync有什么用途以及区别？
     *    解答：主要区别是公平与不公平，即是否需要判断队列中是否还有其它线程等待锁的使用。
     *
     * 3）ReentrantLock中Sync的state是怎么变化的？
     *   解答：state是AQS中volatile int的变量，描述同步的状态。在加锁时+1，释放锁时-1，是线程安全的
     *
     * 4）ReentrantLock为什么lock()和unlock()方法要成对使用？
     *   解答：因为lock()是将state加1，而unlock()将state减1，在释放操作中，只有state=0时，才会把拥有者线程置为null，
     *        也就是其它的线程才能拥有这把锁，若不成对使用，就无法完成释放，则其它线程将无法使用这把锁。
     *
     * 5）ReentrantReadWriteLock读写锁有什么特性？读锁和写锁有什么不同？
     *    解答：可以被多个线程占用，写锁只能被一个线程占用
     *         a）使用读锁时，会检查写锁是否被其它线程占用，若没有则可以使用读锁
     *         b）使用写锁时，会判断当前线程与锁的持有线程是否相同，若不相同则加锁失败，进入AQS队列中
     *            等待拥有写锁的线程执行完逻辑后，将拥有的线程置为null，AQS队列中的写锁即可执行。
     *
     * 6）ReentrantLock中的lockInterruptibly的功能用途是什么？
     */

     /**
     * 场景1：可重入锁基本使用（NonfairSync实现）
     */
    @Test
    public void test_basic_use_v1() {
        Lock nonFailLock = new ReentrantLock(); //默认Sync的实例为NonfairSync
        method1(nonFailLock);

        /**
         * 输出结果：
         * method1 is called
         * method2 is called
         *
         * 结果分析：
         * 1）调用method1()时，已经获取到锁，然后调用method2()时，又再次获取到锁，继续正常执行
         */
    }

    /**
     * 场景2：可重入锁基本使用（FairSync实现）
     */
    @Test
    public void test_basic_use_v2() {

        Lock fairLock = new ReentrantLock(true);
        method1(fairLock);

        /**
         * 输出结果：
         * method1 is called
         * method2 is called
         *
         * 结果分析：
         * 1）ReentrantLock中的FairSync和NonFairSync的主要区别是FailSync在lock时，会判断hasQueuedPredecessors()
         *    队列中是否有其它的线程等待锁，若没有再抢锁。
         */
    }

    private void method1(Lock lock) {
        lock.lock();
        System.out.println("method1 is called");
        method2(lock);
        lock.unlock();
    }

    private void method2(Lock lock) {
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
         * method1 is called，timestamp=Fri Apr 12 13:36:07 CST 2024
         * method2 is called，timestamp=Fri Apr 12 13:36:12 CST 2024
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
         *
         * 结果总结：
         * 1）获取读锁时，会判断是否有独占线程，即有写锁的线程，若有则将线程加入队列等待。
         *    若没有则可以获取读锁，也就是多个线程都可以获取到读锁。
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
         * 1）使用写锁时，没有交替执行，一直执行完一个线程。
         * 2）在执行写锁时，会执行ReentrantReadWriteLock.Sync#tryAcquire(int)方法，会判断当前线程与独占线程是否同一个
         *    若不是同一个，则加入AQS同步器的队列中
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
         * 1）由于Lock的类型为ReentrantLock，属于可重入锁，所以一个线程可以多次获取锁
         * 2）ReentrantLock加锁时，会进入ReentrantLock.Sync#nonfairTryAcquire方法，里面会判断
         *    当前线程和独占线程是否为同一个，若为同一个，则可以加锁成功，即state值加1
         *
         */
    }

    /**
     * 场景6：ReentrantLock高级使用：避免死锁（响应中断、可轮训锁、定时锁）
     */
    @Test
    public void test_advanced_used() {
        long time = System.currentTimeMillis();
        InterruptiblyLock interruptiblyLock = new InterruptiblyLock();
        Thread thread1 = interruptiblyLock.lock1();
        Thread thread2 = interruptiblyLock.lock2();

        while (true) {
            if (System.currentTimeMillis() - time >= 3000) {
                thread2.interrupt();
            }
        }

        /**
         * 输出结果：
         * java.lang.InterruptedException
         * ......
         * Thread-1，退出。
         * Thread-0，执行完毕！
         * Thread-0，退出。
         *
         * 结果分析：
         * 1）ReentrantLock#lockInterruptibly()在加锁不成功时，会一直循环尝试加锁，直到线程被中断
         */
    }

    class ReadWriteDemo {
        private ReentrantReadWriteLock rw = new ReentrantReadWriteLock(); //默认是Sync的是NonfairSync，并且创建了读锁和写锁

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
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void method2() {
            lock.lock();
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
