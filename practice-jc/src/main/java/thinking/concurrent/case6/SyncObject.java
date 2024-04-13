package thinking.concurrent.case6;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class SyncObject {
    public static void main(String[] args) { //在其他对象上加锁
        final DualSynch ds = new DualSynch();
        new Thread() {
            public void run() {
                ds.fn();
            }
        }.start();
        ds.g();
    }
}
