package interview.offer_come.concurrent.thread;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class NewThread extends Thread {

    @Override
    public void run() {
        System.out.println("create a thread by extends Thread");
    }
}
