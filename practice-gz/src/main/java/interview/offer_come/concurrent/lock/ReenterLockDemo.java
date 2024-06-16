package interview.offer_come.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chensy
 * @date 2024/3/28
 */
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
