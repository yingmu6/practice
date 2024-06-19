package relative.thread.condition;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chensy
 * @date 2023/9/21
 */
public class ConditionTest { //@JaY-Doing

    /**
     * 知识点：Condition
     *
     * 知识点概括：
     * 1）在使用Lock之前，我们使用的最多的同步方式应该是synchronized关键字来实现同步方式了。配合Object的wait()、notify()系列方法可以实现等待/通知模式。
     *    Condition接口也提供了类似Object的监视器方法，与Lock配合可以实现等待/通知模式，但是这两者在使用方式以及功能特性上还是有差别的。
     *
     * 2）首先我们需要明白condition对象是依赖于lock对象的，意思就是说condition对象需要通过lock对象进行创建出来(调用Lock对象的newCondition()方法)。
     *    condition的使用方式非常的简单。但是需要注意在调用方法前获取锁。
     *
     * 3）线程间的等待和唤醒，只能是针对同一个Condition对象实例的。若是不同的Condition对象实例，等待和唤醒是不关联的。
     *
     * 关联点学习：
     * 1）创建自定义线程池，可以自定义线程的名称（Doing）
     *
     *
     *
     * 问题点答疑：
     * 1）Condition的功能用途是什么？怎么与锁进行关联的？
     * 2）Condition中的await和signal的底层大致原理是什么？
     *
     *
     * 参考链接：
     * a）https://zhuanlan.zhihu.com/p/312081597 Java并发之Condition详解（用例参考）
     */


    /**
     * 创建锁
     */
    public Lock readLock = new ReentrantLock();
    /**
     * 创建条件
     */
    public Condition condition = readLock.newCondition();

    /**
     * 场景1：condition基本使用
     */
    @Test
    public void test_condition_basic() throws IOException { //Doing
        ConditionTest useCase = new ConditionTest();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(() -> {
            //获取锁进行等待
            useCase.conditionWait();
        });
        executorService.execute(() -> {
            //获取锁进行唤起读锁
            useCase.conditionSignal();
        });

        System.in.read(); //需要加上这句一直进行读取，直到线程任务完成

        /**
         * 输出结果：
         * pool-1-thread-1拿到锁了，time = 1718683898836
         * pool-1-thread-1等待信号，time = 1718683898836
         * pool-1-thread-2拿到锁了，time = 1718683903838
         * pool-1-thread-2发出信号，time = 1718683903838
         * pool-1-thread-2释放锁，time = 1718683903838
         * pool-1-thread-1拿到信号，time = 1718683903838
         * pool-1-thread-1释放锁，time = 1718683903838
         *
         * 结果分析：
         * 1）线程pool-1-thread-1拿到锁后，就使用condition的await进行等待，只有
         *
         *
         * 问题点答疑：
         * 1）同一把锁能创建多个condition对象吗？await等待的线程，是不是要同一个condition对象才能signal？
         *    解答：是的，需要同一个Condition对象。通知和唤醒，是处理同一个Condition对象关联的线程
         *
         * 2）此处是不同的线程，为什么使用同一把锁，能加锁成功？同一个线程可以多次获取到锁，是因为加锁内部会判断
         *    当且线程与锁中维护的独占线程是否是同一个，若是同一个可以继续进行，只要把state加1，当使用完把state减1即可
         *
         */
    }

    /**
     * 场景2：测试Condition的创建
     */
    @Test
    public void test_condition_create() { //Done
        Lock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();
        System.out.println("c1 = " + c1);
        System.out.println("c2 = " + c2);
        System.out.println("(c1 == c2) is " + (c1 == c2));

        /**
         * 输出结果：
         * c1 = java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject@1c655221
         * c2 = java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject@58d25a40
         * (c1 == c2) is false
         *
         * 结果分析：
         * 1）说明每调用一次newCondition()，都会产生新的Condion实例对象
         *    从内部源码看，每次调用都是创建AbstractQueuedSynchronizer中的内部类ConditionObject的实例
         *    该内部类实现了Condition接口
         */
    }

    /**
     * 场景3：等待和通知使用的是不同的Condition
     */
    @Test
    public void test_different_condition() throws IOException { //Done
        ConditionTest useCase = new ConditionTest();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(() -> {
            //获取锁进行等待
            useCase.conditionWait();
        });
        executorService.execute(() -> {
            //获取锁进行唤起读锁
            useCase.conditionSignalV2();
        });

        System.in.read();

        /**
         * 输出结果：
         * pool-1-thread-1拿到锁了，time = 1718683928573
         * pool-1-thread-1等待信号，time = 1718683928573
         * pool-1-thread-2拿到锁了(V2)，time = 1718683933575
         * pool-1-thread-2发出信号(V2)，time = 1718683933575
         * pool-1-thread-2释放锁(V2)，time = 1718683933575
         *
         * 结果分析：
         * 1）因为等待和唤醒使用的Condition对象不是同一个，所以线程2使用Condition去唤醒时，只能
         *    唤醒与同一个Condition对象关联的线程，线程用了另一个Condition，所有收不到信号信息，一直处于阻塞状态
         */
    }

    /**
     * 等待线程
     */
    public void conditionWait() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "拿到锁了，time = " + System.currentTimeMillis());
            System.out.println(Thread.currentThread().getName() + "等待信号，time = " + System.currentTimeMillis());
            condition.await();
            System.out.println(Thread.currentThread().getName() + "拿到信号，time = " + System.currentTimeMillis());
        } catch (Exception e) {

        } finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放锁，time = " + System.currentTimeMillis());
        }
    }

    /**
     * 唤起线程
     */
    public void conditionSignal() {
        readLock.lock();
        try {
            //睡眠5s 线程1启动
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + "拿到锁了，time = " + System.currentTimeMillis());
            condition.signal();
            System.out.println(Thread.currentThread().getName() + "发出信号，time = " + System.currentTimeMillis());
        } catch (Exception e) {

        } finally {
            //释放锁
            readLock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放锁，time = " + System.currentTimeMillis());
        }
    }

    /**
     * 唤起线程（用不同的Condition唤醒线程）
     */
    public void conditionSignalV2() {
        readLock.lock();
        try {
            //睡眠5s 线程1启动
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + "拿到锁了(V2)，time = " + System.currentTimeMillis());
            Condition newCondition = readLock.newCondition();
            newCondition.signal();
            System.out.println(Thread.currentThread().getName() + "发出信号(V2)，time = " + System.currentTimeMillis());
        } catch (Exception e) {

        } finally {
            //释放锁
            readLock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放锁(V2)，time = " + System.currentTimeMillis());
        }
    }
}
