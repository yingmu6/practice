package com.csy.interview.written_exam.collection.queue_ext;

import java.util.Random;
import java.util.concurrent.DelayQueue;

/**
 * @author chensy
 * @date 2023/9/1
 */
public class MyDelayPro implements Runnable {

    private DelayQueue<MyDelayItem> dq;

    private Random r = new Random();

    private int size;

    public MyDelayPro (DelayQueue<MyDelayItem> dq, int size) {
        this.dq = dq;
        this.size = size;
    }

    @Override
    public void run() {

        try {
            while (true) {
                Thread.sleep(1000);
                if (dq.size() >= size) {
                    System.out.println("队列消费慢，生产者暂停生产");
                    continue;
                }

                MyDelayItem item = new MyDelayItem(r.nextInt(10));
                dq.offer(item);
                System.out.println("生产者把产品加入队列：" + item.toString() + " size：" + dq.size());
                System.out.println("产品队列中信息：" + dq.toString());
                System.out.println();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
