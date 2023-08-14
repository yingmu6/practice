package com.csy.interview.no2;

import com.csy.interview.no2.atomic_ext.AtomicDemo;
import org.junit.Test;

import java.io.IOException;

/**
 * @author chensy
 * @date 2023/8/12
 */
public class AtomicTest {

    /**
     * 原子类测试
     */

    /**
     * 场景1：测试自增场景
     */
    @Test
    public void test_increase() throws IOException {
        AtomicDemo atomicDemo = new AtomicDemo();
        for (int i = 0; i < 5; i++) {
            new Thread(atomicDemo).start(); //启动5个线程
        }
        System.in.read();

        /**
         * 输出结果：
         *
         * 线程名：Thread-0, Atomic自增值：4, 普通变量自增值：1, 带上synchronized的自增值：2, 带上Lock的自增值：3
         * 线程名：Thread-1, Atomic自增值：3, 普通变量自增值：0, 带上synchronized的自增值：1, 带上Lock的自增值：4
         * 线程名：Thread-2, Atomic自增值：0, 普通变量自增值：0, 带上synchronized的自增值：3, 带上Lock的自增值：0
         * 线程名：Thread-3, Atomic自增值：2, 普通变量自增值：0, 带上synchronized的自增值：0, 带上Lock的自增值：1
         * 线程名：Thread-4, Atomic自增值：1, 普通变量自增值：0, 带上synchronized的自增值：4, 带上Lock的自增值：2
         *
         * 结果分析：
         * 1）每次输出的结果不一样，但是Atomic修饰的变量，使用AtomicXxx.getAndIncrement()最终结果是符合预期的，也就是线程安全
         * 2）而++的操作，每次的结果不是最终的预期值，也就是线程不安全的
         * 3）在方法上使用synchronized修饰做自增操作，最终结果符合预期，也就是线程安全
         * 4）此处使用lock加锁，但得到的结果不符合预期（todo @csy 此处待探索）
         */
    }
}
