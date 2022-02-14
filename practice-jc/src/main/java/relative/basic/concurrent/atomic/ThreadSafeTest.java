package relative.basic.concurrent.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : chensy
 * Date : 2020-02-16 22:14
 */
public class ThreadSafeTest {
    public static int count = 0;
    public static Counter counter = new Counter();
    public static AtomicInteger atomicInteger = new AtomicInteger(0);
    volatile public static int countVolatile = 0;

    public static void main(String[] args) {
        /**
         * https://www.jianshu.com/p/205a61af1205 CountDownLatch使用及应用场景例子
         * CountDownLatch(闭锁) 这个类能够使一个线程等待其他线程完成各自的工作后再执行。
         * CountDownLatch是通过一个计数器来实现的，计数器的初始值为线程的数量。每当一个线程完成了自己的任务后，
         * 计数器的值就会减1。当计数器值到达0时，它表示所有的线程已经完成了任务，然后在闭锁上等待的线程就可以恢复执行任务
         */
        // 保证所有线程执行完毕.
        final CountDownLatch cdl = new CountDownLatch(10);
        for(int i=0; i<10; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        count++; //最后计算值可能出错
                        counter.increment(); //使用synchronized 确保线程安全
                        atomicInteger.getAndIncrement(); // 值不会改变
                        countVolatile++; //最终值会变化
                    }
                    cdl.countDown();
                }
            }.start();
        }
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("static count: " + count);
        System.out.println("Counter: " + counter.getValue());
        System.out.println("AtomicInteger: " + atomicInteger.intValue());
        System.out.println("countVolatile: " + countVolatile);

        /**
         * static count: 9968     值会变
         * Counter: 10000         值不变，始终保持正确的行为，线程安全
         * AtomicInteger: 10000   值不变，线程安全
         * countVolatile: 9993    值会变
         */
    }
}
