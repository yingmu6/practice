package com.csy.interview.written_exam.thread;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author chensy
 * @date 2023/9/21
 */
public class CASRelaTest {

    /**
     * CAS_测试
     * 1）
     *
     * 2）Java中，使用AtomicStampedReference来解决ABA问题。它包含了属性值和戳记，只有两者都相等时，才进行更新。戳记相当于版本号
     *
     * 参考链接：
     * a）https://zhuanlan.zhihu.com/p/94762520 CAS原理讲解
     * b）https://juejin.cn/post/6844903796129136647  CAS原理分析以及ABA问题详解
     */

    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    private static AtomicStampedReference<Integer> asRef = new AtomicStampedReference<>(1, 0);

    /**
     * 场景1：ABA问题
     *
     * 举例说明：银行取款、存款场景
     * 1）开始账号中100元钱，现在两个线程T1、T2要取50元，都拿到100元然后更新为50元，正常情况下进行比较交换，只有一个线程能成功
     *    当T1线程成功时，账号中的余额为50元，此时T2由于某种原因阻塞了，它还是持有扣减50元的任务。
     * 2）此时T3线程存入50元，账户余额变为100元了，T2线程此时恢复时，发现账户余额为100元，所以能更新成功，最后余额变为50元
     * 3）此场景中最终的余额为50元，但实际应该是100-50+50=100元，所以出现了ABA问题造成了影响（T2线程正确的场景下，是不能更新成功的）
     */
    @Test
    public void test_ABA_problem() throws InterruptedException {
        ABA();

        /**
         * 输出结果：
         * 第一处比较交换的结果：true
         * 第二处比较交换的结果：true
         * 第三处比较交换的结果：true
         *
         * 结果分析：
         * 1）代码1中，由于此时内存值为1，与预期相等，则可以更新成功，返回true
         * 2）由于代码1把变量的内存值更改为2了，代码2处的预期值为2，符合预期，返回true
         * 3）代码3中，由于代码2已经把内存值更改为1了，所以此时更改成功，对代码3来说，是没感知到内存中的值已经从 A->B->A，就出现了ABA问题
         */
    }

    public void ABA() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean c1 = atomicInteger.compareAndSet(1, 2); //代码1）
                System.out.println("第一处比较交换的结果：" + c1);
                boolean c2 = atomicInteger.compareAndSet(2,1); //代码2）
                System.out.println("第二处比较交换的结果：" + c2);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                boolean c3 = atomicInteger.compareAndSet(1, 2); //代码3）
                System.out.println("第三处比较交换的结果：" + c3);
            }
        });

        t1.start();
        t2.start();
        t1.join(); //线程加入，等待t1执行完，当前线程才继续执行
        t2.join();
    }

    /**
     * 场景2：解决ABA问题
     */
    @Test
    public void test_fix_ABA() throws InterruptedException {
        fix_ABA();

        /**
         * 运行结果：
         * 开始睡眠：stamp=0
         * 第一处比较交换的结果：true，stamp=1
         * 第二处比较交换的结果：true，stamp=2
         * 结束睡眠：stamp=2
         * 第三处比较交换的结果：false，stamp=2
         *
         * 结果分析：
         * 1）t1、t2启动后，由于t1睡眠了500毫秒，此时t2先运行，拿到的asRef中的戳记为0，然后t2阻塞1000毫秒
         * 2）t1恢复执行后，会取内存中值作为预期，然后再做更新，两次操作都成功，此时的戳记为2
         * 3）t2线程阻塞1000毫秒后，恢复运行，然后拿着一开始拿到的戳记0去更新，因为此时内存中的戳记已经改为2了，所以更新失败了
         *
         * 结果总结：
         * 1）每次操作后，戳记都会+1，版本号与内存中值不同时，更新失败，这就解决了ABA问题
         */
    }

    public void fix_ABA() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                boolean c1 = asRef.compareAndSet(1, 2, asRef.getStamp(), asRef.getStamp() + 1);
                System.out.println("第一处比较交换的结果：" + c1 + "，stamp=" + asRef.getStamp());
                boolean c2 = asRef.compareAndSet(2, 1, asRef.getStamp(), asRef.getStamp() + 1);
                System.out.println("第二处比较交换的结果：" + c2 + "，stamp=" + asRef.getStamp());

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int stamp = asRef.getStamp();
                System.out.println("开始睡眠：stamp=" + stamp);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("结束睡眠：stamp=" + asRef.getStamp());
                boolean c3 = asRef.compareAndSet(1, 2, stamp, stamp + 1);
                System.out.println("第三处比较交换的结果：" + c3 + "，stamp=" + asRef.getStamp());
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
