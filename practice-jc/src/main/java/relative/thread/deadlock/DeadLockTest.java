package relative.thread.deadlock;

import org.junit.Test;

import java.io.IOException;

/**
 * @author chensy
 * @date 2023/9/24
 */
public class DeadLockTest {

    /**
     * 死锁_测试
     */

    public static Object obj1 = new Object();
    public static Object obj2 = new Object();

    /**
     * 场景1：基本使用
     */
    @Test
    public void test_basic_deadLock() throws IOException {
        Thread t1 = new Thread(new T1());
        Thread t2 = new Thread(new T2());
        t1.start();
        t2.start();

        System.in.read();

        /**
         * 输出结果：
         * Thread t1 is started
         * Thread t1 lock obj1
         * Thread t2 is started
         * Thread t2 lock obj2
         *
         * 结果分析：
         */
    }
}
