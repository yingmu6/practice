package com.csy.interview.no3;

import com.csy.interview.no3.map_ext.TestTask;
import com.csy.interview.no3.map_ext.TestTask2;
import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author chensy
 * @date 2023/8/26
 */
public class ConcurrentHashMapTest {

    /**
     * ConcurrentHashMap_测试
     * 1）判断是否多线程安全
     *   a）看是否共享资源
     *   b）按是否有非原子操作
     *   c）在非原子操作下是否加锁
     *
     * 注明：此处所写的多线程用例，可用于测试多线程下是否线程安全
     */

     /**
     * 场景1：使用单线程计算
     */
    @Test
    public void test_single_thread() {
        int threadNumber = 1;
        System.out.println("单线程运行：");
        for (int i = 0; i < 5; i++) {
            System.out.println("第" + (i + 1) + "次运行结果：" + testAdd(threadNumber, false));
        }

        /**
         * 运行结果：
         *
         * 单线程运行：
         * 第1次运行结果：10
         * 第2次运行结果：10
         * 第3次运行结果：10
         * 第4次运行结果：10
         * 第5次运行结果：10
         *
         * 结果分析：
         * 1）单线程运行结果每次一样，即线程安全的
         */
    }

    /**
     * 场景2：使用多线程计算（线程不安全场景）
     */
    @Test
    public void test_multi_thread_with_unsafe() {
        int threadNumber = 5;
        System.out.println("多线程运行：");
        for (int i = 0; i < 5; i++) {
            System.out.println("第" + (i + 1) + "次运行结果：" + testAdd(threadNumber, false));
        }

        /**
         * 运行结果：
         *
         * 多线程运行：
         * 第1次运行结果：50
         * 第2次运行结果：49
         * 第3次运行结果：50
         * 第4次运行结果：50
         * 第5次运行结果：49
         *
         * 结果分析：
         * 1）从TestTask#run方法的逻辑看，是依次取key=1的元素累加10百次，此处因为有5个线程执行run方法，也就是会执行50次，预期结果就是每个线程运行完成，应该是50
         *    但结果不是50时，说明是线程不安全的。
         *
         * 2）现成不安全原因分析：
         *    执行主体：map.put(1, map.get(1) + 1); 此操作分为三步：
         *    a）map.get(1)
         *    b）+1
         *    c）map.put
         *    虽然a、c都是原子操作，由ConcurrentHashMap来保证线程安全，但是+1就不是原子操作了。比如：Map中的某一刻值为<1,2>，线程1、线程2同时取到值都为2，线程各自+1后值为3
         *    但两个线程各加了1次，本应该是4，结果却为3，所以线程不安全了。
         *
         * 结果总结：
         * 1）在使用ConcurrentHashMap计算值时，尽量避免使用非原子操作，如果使用了非原子操作，就是用锁来实现多线程安全。
         *
         * 问题点答疑：
         * 1）TestTask的成员变量private ConcurrentHashMap<Integer, Integer> map是私有的，不是共享资源，按道理不应该出现线程不安全，是怎么出现的？
         *    解答：并不是说private修饰，就一定是线程安全的，要看多个线程是否共享资源。比如当前5个线程执行testAdd，而该方法里面传入同一个Map，也就是此时这个Map是共享资源了
         */
    }

    /**
     * 场景3：使用多线程计算（线程安全场景）
     */
    @Test
    public void test_multi_thread_with_safe() {
        int threadNumber = 5;
        System.out.println("多线程运行：");
        for (int i = 0; i < 5; i++) {
            System.out.println("第" + (i + 1) + "次运行结果：" + testAdd(threadNumber, true));
        }

        /**
         * 运行结果：
         *
         * 多线程运行：
         * 第1次运行结果：50
         * 第2次运行结果：50
         * 第3次运行结果：50
         * 第4次运行结果：50
         * 第5次运行结果：50
         *
         * 结果分析：
         * 1）在TestTask2#run()使用了synchronized进行加锁处理，保证线程安全
         */
    }

    private int testAdd(int threadNumber, boolean isSafe) {
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
        map.put(1, 0); //初始值
        ExecutorService pool = Executors.newCachedThreadPool(); //创建可缓存的线程池，看源码：corePoolSize=0，maximumPoolSize=Integer.MAX_VALUE，也就是新加入的线程受keepAliveTime存活时间限制，空闲时超过指定时间则回收，适合短时间任务
        for (int i = 0; i < threadNumber; i++) {
            if (isSafe) {
                pool.execute(new TestTask2(map));
            } else {
                pool.execute(new TestTask(map)); //创建线程，并提交到线程池中（多线程下，传入同一个Map，所以此时Map即为共享资源了）
            }
        }
        pool.shutdown(); //停止线程池，不会接收新任务
        try {
            pool.awaitTermination(20, TimeUnit.SECONDS); //阻塞等待，直到线程池中的所有任务完成（给定了等待超时时间为20秒）
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return map.get(1);
    }
}
