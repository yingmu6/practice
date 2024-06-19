package thinking.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class SerialNumberChecker {

    static class SerialNumberGenerator {
        private static volatile int serialNumber = 0;
        public static int nextSerialNumber() {
            return serialNumber++;
        }
    }

    private static final int SIZE = 10;

    private static CircularSet serials =
            new CircularSet(1000);

    private static ExecutorService exec =
            Executors.newCachedThreadPool();

    static class SerialChecker implements Runnable {

        @Override
        public void run() {
            while (true) {
                int serial = SerialNumberGenerator.nextSerialNumber();
                if (serials.contains(serial)) {
                    System.out.println("Duplicate：" + serial);
                    System.exit(0);
                }
                serials.add(serial);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException { //原子性和易变性
        for (int i = 0; i < SIZE; i++) {
            exec.execute(new SerialChecker());
            if (args.length > 0) {
                TimeUnit.SECONDS.sleep(new Integer(args[0]));
                System.out.println("No duplicate detected");
                System.exit(0);
            }
        }
    }
}
