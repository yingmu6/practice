package relative.basic.random;

import org.junit.Test;

import java.io.FilterOutputStream;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author chensy
 * @date 2023/9/20
 */
public class ThreadLocalRandomTest {

    /**
     * ThreadLocalRandom_测试
     * 1）Java introduced the java.util.concurrent.ThreadLocalRandom class in JDK 7 –
     *    for generating random numbers in a multi-threaded environment.（在多线程环境下产生随机数）
     *
     * 2）ThreadLocalRandom is a combination（组合） of the ThreadLocal and Random classes and is isolated to the current thread.
     *
     * 3）生成新的随机数需要两步：
     *    a）根据老的种子生成新的种子
     *    b）由新的种子计算出新的随机数
     *
     * 4）在多线程下，不同线程可能拿着同一个老的种子去计算，由于算法固定，就会产生相同的随机值，随机值变得不随机了，所以源码中使用了CAS来解决多线程下的问题。
     *   但是虽然能解决问题，但是在某一时刻，只有一个线程能操作成功，其它线程就会自旋重试，倒是并发性降低。
     *
     * 5）ThreadLocalRandom保证每个线程都维护一个种子变量，每个线程都根据自己老的种子去生成新的种子，避免竞争问题，大大提高了并发性能
     *
     * 参考链接：
     * a）https://www.baeldung.com/java-thread-local-random
     * b）https://github.com/afkbrb/java-concurrency-note/blob/master/03Java%E5%B9%B6%E5%8F%91%E5%8C%85%E4%B8%AD%E7%9A%84ThreadLocalRandom%E7%B1%BB%E5%8E%9F%E7%90%86%E5%89%96%E6%9E%90.md
     *    (此文档讲解得通俗易通)
     */
    @Test
    public void test_basic() {
        Random random = new Random();
        System.out.println("通过Random产生随机数：" + random.nextInt());
        System.out.println("通过ThreadLocalRandom产生随机数：" + ThreadLocalRandom.current().nextInt());

        /**
         * 输出结果：
         * 通过Random产生随机数：1263249607
         * 通过ThreadLocalRandom产生随机数：953685426
         *
         * 结果分析：
         * 1）Random创建时，可以指定种子范围值，若不指定，基于时间戳产生种子
         * 2）Random使用同一个AtomicLong seed共享变量，在执行next()方法时，会通过CAS保证线程安全
         */
    }
}
