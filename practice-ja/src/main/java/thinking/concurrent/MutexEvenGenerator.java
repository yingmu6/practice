package thinking.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class MutexEvenGenerator extends IntGenerator {

    private int currentEvenValue = 0;

    private Lock lock = new ReentrantLock();

    @Override
    public int next() {
        lock.lock();
        try {
            ++currentEvenValue;
            Thread.yield();
            ++currentEvenValue;
            return currentEvenValue;
        } finally {
            lock.lock();
        }
    }

    public static void main(String[] args) { //使用显示的Lock对象
        EvenChecker.test(new MutexEvenGenerator());
    }
}
