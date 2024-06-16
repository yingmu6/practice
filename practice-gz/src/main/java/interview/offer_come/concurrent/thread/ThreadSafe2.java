package interview.offer_come.concurrent.thread;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class ThreadSafe2 extends Thread {

    public void run() {
        while (!isInterrupted()) {
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
