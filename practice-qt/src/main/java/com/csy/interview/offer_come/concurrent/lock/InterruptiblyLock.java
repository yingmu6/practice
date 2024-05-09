package com.csy.interview.offer_come.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class InterruptiblyLock {
    public ReentrantLock lock1 = new ReentrantLock();
    public ReentrantLock lock2 = new ReentrantLock();

    public Thread lock1() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock1.lockInterruptibly();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock2.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "，执行完毕！");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (lock1.isHeldByCurrentThread()) {
                        lock1.unlock();
                    }

                    if (lock2.isHeldByCurrentThread()) {
                        lock2.unlock();
                    }

                    System.out.println(Thread.currentThread().getName() + "，退出。");
                }
            }
        });
        t.start();
        return t;
    };

    public Thread lock2() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock2.lockInterruptibly(); //若加锁不成功，一直循环尝试加锁，直到线程被中断
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    lock1.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "，执行完毕！");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (lock1.isHeldByCurrentThread()) {
                        lock1.unlock();
                    }

                    if (lock2.isHeldByCurrentThread()) {
                        lock2.unlock();
                    }

                    System.out.println(Thread.currentThread().getName() + "，退出。");
                }
            }
        });

        t.start();
        return t;
    }
}
