package relative.basic.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁
 * @author : chensy
 * Date : 2020-02-16 23:43
 */
public class ReentrantLockTest extends Thread {
    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    public ReentrantLockTest(String name) {
        super.setName(name);
    }

    @Override
    public void run() {
        for (int j = 0; j < 100; j++) {
            lock.lock(); //获取锁
            lock.lock(); // 可多次获取锁，获取几次，就要释放几次
            try {
                System.out.println(this.getName() + " " + i);
                i++;
            } finally {
                lock.unlock(); //释放锁
                lock.unlock();
            }
        }
    }

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTest test1 = new ReentrantLockTest("thread1");
        ReentrantLockTest test2 = new ReentrantLockTest("thread2");

        test1.start();
        test2.start();
        test1.join(); // join将多个线程合并成一个线程
        test2.join();
        System.out.println(i); // 加锁后，线程安全，结果正确
    }
}
/**
 * Lock实现提供比使用synchronized方法和语句可以获得的更广泛的锁定操作
 * 锁是用于通过多个线程控制对共享资源的访问的工具。
 */

/**
 * https://juejin.im/post/5aeb0a8b518825673a2066f0  理解ReentrantLock
 * 重入性: 表示能够对共享资源能够重复加锁，即当前线程多次获取锁而不会被阻塞
 * 关键字synchronized隐式支持重入性
 *
 * ReentrantLock支持两种锁：公平锁和非公平锁。何谓公平性，是针对获取锁而言的，
 * 如果一个锁是公平的，那么锁的获取顺序就应该符合请求上的绝对时间顺序，满足FIFO
 *
 * ReentrantLock并不是一种替代内置加锁的方法，而是作为一种可选择的高级功能。
 * 相比于synchronized，ReentrantLock在功能上更加丰富，它具有可重入、可中断、可限时、公平锁等特点。
 *
 */
