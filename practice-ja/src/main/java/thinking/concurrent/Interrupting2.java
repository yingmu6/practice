package thinking.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class Interrupting2 {

    static class BlockedMutex {
        private Lock lock = new ReentrantLock();
        public BlockedMutex() {
            lock.lock();
        }
        public void f() {
            try {
                lock.lockInterruptibly();
                print("lock acquired in f()");
            } catch (InterruptedException e) {
                print("Interrupted from lock acquisition in f()");
            }
        }
    }

    static class Blocked2 implements Runnable {
        BlockedMutex blocked = new BlockedMutex();
        public void run() {
            print("Waiting for f() in BlockedMutex");
            blocked.f();
            print("Broken out of blocked call");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Blocked2());
        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Issuing t.interrupt()");
        t.interrupt();
    }
}
