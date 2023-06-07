package relative.thread;

import org.junit.Test;

/**
 * 多线程通过final访问主线程局部变量
 * https://xtuhcy.iteye.com/blog/2170295
 *
 * 有关线程安全的探讨--final、static、单例、线程安全
 * https://www.cnblogs.com/bellkosmos/p/5340711.html
 *
 * @author chensy
 * @date 2019-05-29 14:36
 */
public class ThreadTest {

    /**
     * 线程_概述
     * 1）
     *
     * 参考链接：
     * a）https://docs.oracle.com/javase/tutorial/essential/concurrency/runthread.html Oracle官网关于Thread的介绍
     */

    /**
     * 场景1：线程的创建
     */

    // 单线程使用
    @Test
    public void test_single_thread() {
        SafeVar safeVar = new SafeVar();
        Thread thread = new Thread(safeVar);
        thread.start();
    }

    // 多线程处理
    @Test
    public void test_multi_thread() {
       SafeVar safeVar1 = new SafeVar();
       SafeVar safeVar2 = new SafeVar();

       // Thread的run方法是启动线程后，线程获取到资源后，回调run方法的，不用主动去掉，主动去掉用的，只是一个普通方法，并没有发挥线程作用
       // safeVar1.run();
       // safeVar2.run();

       // 多线程时，对于共享变量，有线程安全问题
       Thread thread1 = new Thread(safeVar1);
       Thread thread2 = new Thread(safeVar2);
       thread1.start();
       thread2.start();

       System.out.println("共享变量值：" + SafeVar.var); //此处输出的共享变量值，可能有多种，因为该语句是在主线程中，其它线程还在运算中
    }
}
