package thinking.concurrent.case2;

/**
 * @author chensy
 * @date 2024/4/13
 */
public class DaemonSpawn implements Runnable {
    @Override
    public void run() {
        while (true) {
            Thread.yield();
        }
    }
}
