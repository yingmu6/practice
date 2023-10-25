package com.csy.interview.no2;

import com.csy.interview.no2.thread_ext.ErrHandler;
import com.csy.interview.no2.thread_ext.TestThread;
import com.csy.interview.no2.thread_ext.ThreadException;
import org.junit.Test;

import java.io.IOException;

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
            t1.join(); //等到t1执行结束（join：线程加入、线程合并）
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
     * 3）使用stop方法
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

    @Test
    public void test_interrupt_thread() throws Exception { //可安全退出线程
        SafeInterruptThread thread = new SafeInterruptThread();
        thread.start();

        thread.sleep(1000);
        thread.interrupt();

        /**
         * 输出结果：
         * 线程未中断时，正常业务处理
         *
         * 结果分析：
         * todo @csy 哪种方式才能输出 "线程已中断时，清理工作"
         */
    }

    public class SafeInterruptThread extends Thread {
        @Override
        public void run() {
            if(!Thread.currentThread().isInterrupted()) { //线程未中断
                try {
                    //...处理正常的业务逻辑...
                    sleep(10);
                    System.out.println("线程未中断时，正常业务处理");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); //重新设置中断标识
                }
            }

            if (Thread.currentThread().isInterrupted()) {
                //...处理线程结束前必要的一些资源释放以及清理工作
                System.out.println("线程已中断时，清理工作");
            }
        }
    }

    /**
     * 场景6：守护线程测试
     */
    @Test
    public void test_daemon_thread() {
        System.out.println("test3:begin");
        Thread t1 = new ThreadDemo();
        t1.setDaemon(true); //设置t1为守护线程
        t1.start();
        System.out.println("test3:end");

        /**
         * 输出结果：
         * test3:begin
         * test3:end
         * Thread-0:begin
         *
         * 结果分析：
         * 1）没有输出Thread-0:end，因为t1为守护线程。main方法执行完后，程序中只有t1守护线程进行，只有守护线程运行时，JVM就可以退出了
         *
         * 结果总结：
         * 1）守护线程的典型用例是垃圾回收器，只要JVM启动，它始终会运行，实时监控和管理系统中可被回收的资源
         */
    }

    class ThreadDemo extends Thread {
        public void run() {
            System.out.println(Thread.currentThread().getName() + ":begin");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":end");
        }
    }

    /**
     * 场景7：线程加入、合并join使用
     */
    @Test
    public void test_thread_join() throws IOException {
        Thread t = new Thread(new ThreadImp());
        t.start();
        try {
            Long curTime = System.currentTimeMillis();
            System.out.println("当前时间：" + curTime);
            t.join(1000); // 1）当前线程等待线程t执行结束1000毫秒
            if (t.isAlive()) {
                System.out.println("t has not finished，timeStamp = " + System.currentTimeMillis()); // 2）
            } else {
                System.out.println("t has finished，timeStamp = " + System.currentTimeMillis()); // 3)
            }
            System.out.println("joinFinish，timeStamp = " + System.currentTimeMillis()); // 4）
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.in.read();

        /**
         * 输出结果：
         *
         * 当前时间：1694487519134
         * Begin ThreadImp，timeStamp = 1694487519134
         * t has not finished，timeStamp = 1694487520139
         * joinFinish，timeStamp = 1694487520139
         * End ThreadImp，timeStamp = 1694487524138
         *
         * 结果分析：
         * 1）代码1）处：当前线程等待线程t执行1000毫秒
         * 2）由于线程t的执行体run中，睡眠5秒，所以1秒后还没执行完，当前线程等待指定时间后，就可以恢复执行
         * 3）因为线程t还在执行，所以t.isAlive()检查线程是否还存活时，线程时存活的。
         * 4）执行完代码4）后，在等待一段时间，线程t也执行完了
         *
         * 结果总结：
         * 1）join将两个线程合并，可以让线程等待另一个线程执行。
         */
    }

    class ThreadImp implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("Begin ThreadImp，timeStamp = " + System.currentTimeMillis()); // 5）
                Thread.sleep(5000);
                System.out.println("End ThreadImp，timeStamp = " + System.currentTimeMillis()); // 6）
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 场景8：捕获线程抛出的异常
     */
    @Test
    public void test_catch_thread_exception_v1() {
        Thread thread = new ThreadException();
        try {
            thread.start();
        } catch (Exception e) {
            System.out.println("exception msg:" + e.getMessage());
        }

        /**
         * 输出结果：
         * Exception in thread "Thread-0" java.lang.ArithmeticException: / by zero
         * 	at com.csy.interview.no2.thread_ext.ThreadException.run(ThreadException.java:10)
         *
         * 结果分析：
         * 1）try/catch无法捕获线程中抛出的异常，因为线程时一个独立的执行代码片段。
         */
    }

    @Test
    public void test_catch_thread_exception_v2() {
        Thread thread = new ThreadException();
        try {
            Thread.UncaughtExceptionHandler handler = new ErrHandler();
            thread.setUncaughtExceptionHandler(handler); //为线程设置异常处理类
            thread.start();
        } catch (Exception e) {
            System.out.println("exception msg:" + e.getMessage());
        }

        /**
         * 输出结果：
         *
         * This is:Thread-0,Message:/ by zero
         * java.lang.ArithmeticException: / by zero
         * 	at com.csy.interview.no2.thread_ext.ThreadException.run(ThreadException.java:10)
         * Simulate clean up
         *
         * 结果分析：
         * 1）在线程中发生异常时，会调用异常处理类的ErrHandler的uncaughtException方法，可在该方法中记录异常日志以及资源清理等
         *
         * 结果总结：
         * 1）查看源码，线程处理EventDispatchThread#pumpOneEventForFilters中若捕获到异常，会先查找线程中设置的UncaughtExceptionHandler
         *    然后把异常信息传入该方法中，就可以处理相关的业务逻辑了。
         */
    }
}
