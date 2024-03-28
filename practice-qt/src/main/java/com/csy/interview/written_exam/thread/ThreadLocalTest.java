package com.csy.interview.written_exam.thread;

import org.junit.Test;

import java.io.IOException;

/**
 * @author chensy
 * @date 2023/9/13
 */
public class ThreadLocalTest {

    /**
     * ThreadLocal_测试
     * 1）This gives us the ability to store data individually（单独地） for the current thread and simply wrap it within a special type of object.
     *
     * 2）The TheadLocal construct allows us to store data that will be accessible only by a specific thread.
     *
     * 参考链接：
     * a）https://www.baeldung.com/java-threadlocal
     * b）https://segmentfault.com/a/1190000042232598/en ThreadLocal源码分析
     */

    private ThreadLocal<Integer> arg = new ThreadLocal<>();

    /**
     * 场景1：基本使用
     */
    @Test
    public void test_basic() {
        Thread t1 = new Thread(() -> {
            arg.set(100);
            task1();
        });
        t1.setName("线程1");
        t1.start();

        Thread t2 = new Thread(() -> {
            arg.set(200);
            task1();
        });
        t2.setName("线程2");
        t2.start();

        /**
         * 输出结果：
         * 线程1:100
         * 线程2:200
         *
         * 结果分析：
         * 1）线程运行体run中，设置ThreadLocal值后，task1调用task2方法，并没有通过方法传参
         *   但在task2中能够获取ThreadLocal的值，说明ThreadLocal存储的是线程相关的值，代码的任意地方都能获取到
         *
         * 2）task2方法虽然被两个线程调用，但ThreadLocal的值并不冲突，每个线程维护的值，只属于该线程
         */

    }

    private void task1() {
        task2();
    }

    private void task2() {
        System.out.println(Thread.currentThread().getName() + ":" + arg.get());
    }

    private ThreadLocal<Double> localArg = ThreadLocal.withInitial(() -> Math.random()) ; //创建线程局部变量，变量的初始值由执行的方法给出

    /**
     * 场景2：Java8中新的使用方式 ThreadLocal.withInitial(Supplier)
     */
    @Test
    public void test_new_way_in_java8() throws IOException {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ", 获取数据：" + localArg.get());
            System.out.println(Thread.currentThread().getName() + ", 再次获取数据：" + localArg.get());
        });
        t1.setName("线程1");
        t1.start();

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ", 获取数据：" + localArg.get());
            System.out.println(Thread.currentThread().getName() + ", 再次获取数据：" + localArg.get());
        });
        t2.setName("线程2");
        t2.start();
        System.in.read();

        /**
         * 输出结果：
         * 线程1, 获取数据：0.8998348934453803
         * 线程2, 获取数据：0.18284506246410692
         * 线程1, 再次获取数据：0.8998348934453803
         * 线程2, 再次获取数据：0.18284506246410692
         *
         * 结果分析：
         * 1）同一个线程中的值相同的，而不同的线程中的值是不同的。是因为withInitial()方法中使用SuppliedThreadLocal能动态产生初始化值
         */
    }
}
