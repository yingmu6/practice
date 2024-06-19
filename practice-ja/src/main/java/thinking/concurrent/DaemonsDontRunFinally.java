package thinking.concurrent;

import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class DaemonsDontRunFinally {

    static class ADaemon implements Runnable {
        @Override
        public void run() {
            try {
                print("Starting ADamon");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                print("Exiting via InterruptedException");
            } finally {
                print("This should always run?");
            }
        }
    }

    public static void main(String[] args) {
        Thread t = new Thread(new ADaemon());
        t.setDaemon(true);
        t.start();
    }
}
