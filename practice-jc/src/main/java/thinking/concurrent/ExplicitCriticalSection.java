package thinking.concurrent;

import thinking.concurrent.CriticalSection;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class ExplicitCriticalSection {
    static class ExplicitPairManager1 extends CriticalSection.PairManager {
        private Lock lock = new ReentrantLock();
        @Override
        public synchronized void increment() {
            lock.lock();
            try {
                p.incrementX();
                p.incrementY();
                store(getPair());
            } finally {
                lock.unlock();
            }
        }
    }

    static class ExplicitPairManager2 extends CriticalSection.PairManager {
        private Lock lock = new ReentrantLock();
        @Override
        public void increment() {
            CriticalSection.Pair temp;
            lock.lock();
            try {
                p.incrementX();
                p.incrementY();
                temp = getPair();
            } finally {
                lock.unlock();
            }
            store(temp);
        }
    }

    public static void main(String[] args) {
        CriticalSection.PairManager
                pman1 = new ExplicitPairManager1(),
                pman2 = new ExplicitPairManager2();
        CriticalSection.testApproaches(pman1, pman2);
    }
}
