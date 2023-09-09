package com.csy.interview.no2;

import com.csy.interview.no2.thread_ext.TestThread;
import org.junit.Test;

import java.security.PublicKey;

/**
 * @author chensy
 * @date 2023/7/13
 */
public class ThreadRelaTest {

    /**
     * 线程_测试
     */

    /**
     * 场景1：测试线程中的run方法
     * Thread的run方法，正常情况下，是调用start以后，由系统调用。若直接调用run方法，就和调用普通方法一样
     */
    @Test
    public void test_run() {
        Thread t = new Thread() {
            public void run() {
                pong();
            }
        };
        t.run();
        System.out.println("ping");

        /**
         * 输出结果：
         * pong
         * ping
         *
         * 结果分析：
         * 直接调用线程的run方法，就和调用普通方法一样，就没有用到线程的特性了
         */
    }

    /**
     * 场景2：调用start方法，由系统调用run方法
     */
    @Test
    public void test_start() {
        Thread t = new Thread() {
            public void run() {
                pong();
            }
        };
        t.start();
        System.out.println("ping");

        /**
         * 输出结果：
         * ping
         * pong
         *
         * 结果分析：
         * 调用线程的start方法，由系统调用run方法
         */
    }

    private void pong() {
        System.out.println("pong");
    }

    public volatile static int TEST = 0;
    /**
     * 场景3：异步计算
     */
    @Test
    public void test_calculate() {
        Thread t1 = new TestThread("test1");
        Thread t2 = new TestThread("test2");
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
        System.out.println(ThreadRelaTest.TEST);

        /**
         * 输出结果：
         * test1 thread end 1000843 (此处的值，多次运行结果不一样)
         * test2 thread end 2000000
         * 2000000
         *
         * 结果分析：
         * TestThread中的代码1）、2）加上了synchronized、volatile等做加锁处理，所以是线程安全的
         * 所有TestThread中的代码3）就不会输出
         */
    }

    volatile String[] arr = new String[3]; //volatile可以修饰数组（但是作用在数组的引用上，而不是数组的内容）

    /**
     * 场景4：volatile使用
     */
    @Test
    public void test_volatile() {
        System.out.println(arr);
    }

    /**
     * 场景5：线程终止方法
     * 1）设置flag标志来控制
     * 2）使用interrupt方法
     * ...还有其它方式...
     */
    @Test
    public void test_stop_thread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread go to sleep");
                try {
                    Thread.sleep(50000);
                    System.out.println("thread finish");
                } catch (InterruptedException e) {
                    System.out.println("thread is interrupted");
                }
            }
        });

        thread.start();
        thread.interrupt();

        /**
         * 输出结果：
         * thread go to sleep
         * thread is interrupted
         *
         * 结果分析：
         * 1）线程处于阻塞状态，调用interrupt()会抛出中断异常，所以捕获这个异常可以安全结束线程。
         */
    }
}
