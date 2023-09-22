package relative.thread.condition;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chensy
 * @date 2023/9/21
 */
public class ConditionTest {

    /**
     * Condition_测试
     *
     * 参考链接：
     * a）https://zhuanlan.zhihu.com/p/312081597 Java并发之Condition详解（用例参考）
     */

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    @Test
    public void test_condition_basic() {

    }

    private void conditionWait() {

    }
}
