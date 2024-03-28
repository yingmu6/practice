package com.csy.interview.written_exam.thread.atomic_ext;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chensy
 * @date 2023/8/12
 */
public class AtomicDemo implements Runnable {

    private static AtomicInteger num = new AtomicInteger(0);
    private static int num1 = 0;

    private static int num2 = 0;

    private static int num3 = 0;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {

        }
        System.out.println("线程名：" + Thread.currentThread().getName() + ", Atomic自增值：" + getAutoIncrease() + ", 普通变量自增值：" + getIncrease() + ", 带上synchronized的自增值：" + getIncreaseV2() + ", 带上Lock的自增值：" + getIncreaseV3());
    }

    public int getAutoIncrease() {
        return num.getAndIncrement();
    }

    public int getIncrease() {
        return num1++;
    }

    public synchronized int getIncreaseV2() {
        return num2++;
    }

    public int getIncreaseV3() {
        Lock lock = new ReentrantLock();
        lock.lock();
        int returnVal = num3++;
        lock.unlock();
        return returnVal;
    }
}
