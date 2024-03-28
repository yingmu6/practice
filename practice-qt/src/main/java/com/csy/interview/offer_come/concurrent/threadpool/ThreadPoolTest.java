package com.csy.interview.offer_come.concurrent.threadpool;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chensy
 * @date 2024/3/23
 */
public class ThreadPoolTest {

    /**
     * 知识点：线程池
     *
     * 总结概括：
     *
     * 参考链接：
     *
     */

    /**
     * 场景1：自定义拒绝策略
     */
    @Test
    public void customRejectedStrategy() {

    }

    /**
     * 场景2：线程池的创建方式
     */
    @Test
    public void createThreadPool() {
        // 方式一：
        ExecutorService e1 = Executors.newCachedThreadPool();

        // 方式二：
        ExecutorService e2 = Executors.newFixedThreadPool(5);

        // 方式三：
        ExecutorService e3 = Executors.newSingleThreadExecutor();

        // 方式四：
        ExecutorService e4 = Executors.newWorkStealingPool();
    }
}
