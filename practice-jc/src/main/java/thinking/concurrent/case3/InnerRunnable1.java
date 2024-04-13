package thinking.concurrent.case3;

import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class InnerRunnable1 {

    private int countDown = 5;

    private Inner inner;

    private class Inner implements Runnable {

        Thread t;

        Inner(String name) {
            t = new Thread(this, name);
            t.start();
        }

        @Override
        public void run() {
            try {
                while (true) {
                    print(this);
                    if (--countDown == 0) {
                        return;
                    }
                    TimeUnit.MILLISECONDS.sleep(10);
                }
            } catch (InterruptedException e) {
                print("sleep() interrupted");
            }
        }

        public String toString() {
            return t.getName() + "：" + countDown;
        }
    }

    public InnerRunnable1(String name) {
        inner = new Inner(name);
    }
}
