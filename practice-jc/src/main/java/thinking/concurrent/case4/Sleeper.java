package thinking.concurrent.case4;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class Sleeper extends Thread {

    private int duration;

    public Sleeper(String name, int sleepTime) {
        super(name);
        duration = sleepTime;
        start();
    }

    public void run() {
        try {
            sleep(duration);
        } catch (InterruptedException e) {
            print(getName() + "was interrupted. " + "isInterrupted()：" + isInterrupted());
            return;
        }
        print(getName() + " has awakened");
    }
}
