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
     * 知识点：Condition
     *
     * 问题点：
     * 1）Condition的功能用途是什么？怎么与锁进行关联的？
     *
     * 参考链接：
     * a）https://zhuanlan.zhihu.com/p/312081597 Java并发之Condition详解（用例参考）
     */

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    @Test
    public void test_condition_basic() { //todo @Ym 待测试

    }

    private void conditionWait() {

    }
}
