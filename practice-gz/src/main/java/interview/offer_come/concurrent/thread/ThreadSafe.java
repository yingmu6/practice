package interview.offer_come.concurrent.thread;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class ThreadSafe extends Thread {

    public volatile  boolean exit = false;

    @Override
    public void run() {
        while (!exit) {
            //....
        }
    }
}
