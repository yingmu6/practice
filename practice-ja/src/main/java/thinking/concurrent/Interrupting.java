package thinking.concurrent;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class Interrupting {

    static class SleepBlocked implements Runnable {
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                print("InterruptedException");
            }
            print("Exiting SleepBlocked.run()");
        }
    }

    static class IOBlocked implements Runnable {
        private InputStream in;
        public IOBlocked(InputStream is) {
            in = is;
        }
        public void run() {
            try {
                print("Waiting for read()：");
                in.read();
            } catch (IOException e) {
                if (Thread.currentThread().isInterrupted()) {
                    print("Interrupted from blocked I/O");
                } else {
                    throw new RuntimeException(e);
                }
            }
            print("Exiting IOBlocking.run()");
        }
    }

    static class SynchronizedBlocked implements Runnable {
        public synchronized void f() {
            while (true) {
                Thread.yield();
            }
        }
        public SynchronizedBlocked() {
            new Thread() {
                public void run() {
                    f();
                }
            }.start();
        }
        public void run() {
            print("Trying to call f()");
            f();
            print("Exiting SynchronizedBlocked.run()");
        }
    }


    private static ExecutorService exec =
            Executors.newCachedThreadPool();

    static void test(Runnable r) throws InterruptedException {
        Future<?> f = exec.submit(r);
        TimeUnit.MILLISECONDS.sleep(100);
        print("Interrupting " + r.getClass().getName());
        f.cancel(true);
        print("Interrupt sent to " + r.getClass().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        test(new SleepBlocked());
        test(new IOBlocked(System.in));
        test(new SynchronizedBlocked());
        TimeUnit.SECONDS.sleep(3);
        print("Aborting with System.exit(0)");
        System.exit(0);
    }
}
