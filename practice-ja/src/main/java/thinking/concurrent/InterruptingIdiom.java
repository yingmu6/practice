package thinking.concurrent;

import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class InterruptingIdiom {

    static class NeedsCleanup {
        private final int id;
        public NeedsCleanup(int ident) {
            id = ident;
            print("NeedsCleanup " + id);
        }
        public void cleanup() {
            print("Cleaning up " + id);
        }
    }

    static class Blocked3 implements Runnable {
        private volatile double d = 0.0;
        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    NeedsCleanup n1 = new NeedsCleanup(1);
                    try {
                        print("Sleeping");
                        TimeUnit.SECONDS.sleep(1);
                        NeedsCleanup n2 = new NeedsCleanup(2);
                        try {
                            print("Calculating");
                            for (int i = 0; i < 250000; i++) {
                                d = d + (Math.PI + Math.E) / d;
                            }
                            print("Finished time-consuming operation");
                        } finally {
                            n2.cleanup();
                        }
                    } finally {
                        n1.cleanup();
                    }
                }
            } catch (InterruptedException e){
                print("Exiting via InterruptedException");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 1) {
            print("usage：java InterruptingIdiom delay-in-ms");
            System.exit(1);
        }
        Thread t = new Thread(new Blocked3());
        t.start();
        TimeUnit.MILLISECONDS.sleep(new Integer(args[0]));
        t.interrupt();
    }
}
