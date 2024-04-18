package thinking.concurrent;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class SyncObject {

    static class DualSynch {
        private Object syncObject = new Object();
        public synchronized void fn() {
            for (int i = 0; i < 5; i++) {
                print("f()");
                Thread.yield();
            }
        }
        public void g() {
            synchronized (syncObject) {
                for (int i = 0; i < 5; i++) {
                    print("g()");
                    Thread.yield();
                }
            }
        }
    }

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
