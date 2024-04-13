package thinking.concurrent.case2;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class DaemonsDontRunFinally {
    public static void main(String[] args) {
        Thread t = new Thread(new ADaemon());
        t.setDaemon(true);
        t.start();
    }
}
