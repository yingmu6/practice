package com.csy.interview.offer_come.concurrent.queue;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;

/**
 * @author chensy
 * @date 2024/1/20
 */
public class SynchronousQueueTest {

    /**
     * SynchronousQueue是一个不存储元素的阻塞队列
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
         *
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
