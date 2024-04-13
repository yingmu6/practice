package thinking.concurrent.case3;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class InnerThread1 {

    private int countDown = 5;

    private Inner inner;

    private class Inner extends Thread {
        Inner(String name) {
            super(name);
            start();
        }

        public void run() {
            try {
                while (true) {
                    print(this);
                    if (--countDown == 0) {
                        return;
                    }
                    sleep(10);
                }
            } catch (InterruptedException e) {
                print("interrupted");
            }
        }

        public String toString() {
            return getName() + "：" + countDown;
        }
    }

    public InnerThread1(String name) {
        inner = new Inner(name);
    }
}
