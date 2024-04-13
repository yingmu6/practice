package thinking.concurrent.case7;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class Blocked2 implements Runnable {

    BlockedMutex blocked = new BlockedMutex();

    @Override
    public void run() {
        print("Waiting for f() in BlockedMutex");
        blocked.f();
        print("Broken out of blocked call");
    }
}
