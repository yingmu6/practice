package thinking.concurrent.case2;

import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.printnb;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class Daemons {
    public static void main(String[] args) throws InterruptedException {
        Thread d = new Thread(new Daemon());
        d.setDaemon(true);
        d.start();
        printnb("d.isDaemon() = " + d.isDaemon() + "，");
        TimeUnit.SECONDS.sleep(1);
    }
}
