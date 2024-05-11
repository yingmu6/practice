package com.csy.interview.offer_come.concurrent.queue;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;

/**
 * @author chensy
 * @date 2024/1/20
 */
public class SynchronousQueueTest { //@MsY-Doing

    /**
     * 知识点：SynchronousQueue
     *
     * 知识点概括：
     * 1）SynchronousQueue，实际上它不是一个真正的队列，因为SynchronousQueue没有容量。与其他BlockingQueue（阻塞队列）不同,
     *    SynchronousQueue是一个不存储元素的BlockingQueue。只是它维护一组线程，这些线程在等待着把元素加入或移出队列。
     *
     * 问题点答疑：
     * 1）SynchronousQueue底层是怎么存储的？原理是什么？
     *
     * 参考链接：https://juejin.cn/post/7031196740128768037
     */

    /**
     * 场景1：
     */
    @Test
    public void test_basic_use() {
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();
        new Producer(queue).start();
        new Customer(queue).start();

        /**
         * 输出结果：
         * product a data:65
         * customer a data:65
         * product a data:184
         * customer a data:184
         * product a data:786
         * customer a data:786
         * ......
         *
         * 结果分析：
         * 1）Producer、Customer都是线程，接收SynchronousQueue处理，put放入的元素等待take获取
         */
    }

    class Producer extends Thread { //提供者线程

        SynchronousQueue<Integer> queue;

        public Producer(SynchronousQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    int product = new Random().nextInt(1000);
                    queue.put(product); //不存储元素
                    System.out.println("product a data:" + product);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Customer extends Thread { //消费者线程

        SynchronousQueue<Integer> queue;

        public Customer(SynchronousQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    int data = queue.take();
                    System.out.println("customer a data:" + data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
