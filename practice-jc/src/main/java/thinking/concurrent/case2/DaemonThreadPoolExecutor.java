package thinking.concurrent.case2;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chensy
 * @date 2024/4/13
 */
public class DaemonThreadPoolExecutor extends ThreadPoolExecutor {
    public DaemonThreadPoolExecutor() {
        super(0, Integer.MIN_VALUE, 60L, TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                new DaemonThreadFactory());
    }
}
