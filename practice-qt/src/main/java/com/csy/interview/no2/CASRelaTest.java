package com.csy.interview.no2;

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
     *
     * 参考链接：
     * a）https://zhuanlan.zhihu.com/p/94762520 CAS原理讲解
     * b）https://juejin.cn/post/6844903796129136647  CAS原理分析以及ABA问题详解
     */

    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    private static AtomicStampedReference<Integer> asRef = new AtomicStampedReference<>(100, 0);

    /**
     * 场景1：ABA问题
     *
     * 举例说明：银行取款、存款
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
    public void test_resolve_ABA() throws InterruptedException {
        resolve_ABA();

        /**
         * 运行结果：
         * Before sleep：stamp=0
         * After sleep：stamp=0
         * false
         *
         * 结果分析：
         */
    }

    public void resolve_ABA() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                asRef.compareAndSet(1, 2, asRef.getStamp(), asRef.getStamp() + 1);
                asRef.compareAndSet(2, 1, asRef.getStamp(), asRef.getStamp() + 1);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int stamp = asRef.getStamp();
                System.out.println("Before sleep：stamp=" + stamp);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("After sleep：stamp=" + asRef.getStamp());
                boolean c3 = asRef.compareAndSet(1, 2, stamp, stamp + 1);
                System.out.println(c3);
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
