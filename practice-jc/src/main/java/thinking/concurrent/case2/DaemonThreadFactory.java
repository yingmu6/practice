package thinking.concurrent.case2;

import java.util.concurrent.ThreadFactory;

/**
 * @author chensy
 * @date 2024/4/13
 */
public class DaemonThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }
}
