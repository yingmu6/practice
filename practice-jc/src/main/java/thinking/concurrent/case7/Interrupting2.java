package thinking.concurrent.case7;

import java.util.concurrent.TimeUnit;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class Interrupting2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Blocked2());
        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Issuing t.interrupt()");
        t.interrupt();
    }
}
