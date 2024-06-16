package interview.offer_come.concurrent.thread;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class SafeInterruptThread extends Thread {

    @Override
    public void run() {
        if (!Thread.currentThread().isInterrupted()) {
            try {
                sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        if (Thread.currentThread().isInterrupted()) {
            try {
                sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
