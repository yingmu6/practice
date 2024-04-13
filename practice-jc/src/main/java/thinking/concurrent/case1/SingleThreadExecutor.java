package thinking.concurrent.case1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chensy
 * @date 2024/4/13
 */
public class SingleThreadExecutor {
    public static void main(String[] args) {
        ExecutorService exec =
                Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            exec.execute(new LiftOff());
        }
        exec.shutdown();
    }
}
