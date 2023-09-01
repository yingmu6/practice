package com.csy.interview.no3.queue_ext;

import java.util.concurrent.DelayQueue;

/**
 * @author chensy
 * @date 2023/9/1
 */
public class MyDelayCus implements Runnable {

    private DelayQueue<MyDelayItem> dq;

    public MyDelayCus (DelayQueue<MyDelayItem> dq) {
        this.dq = dq;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(5000);
                MyDelayItem di = dq.take();
                System.out.println("消费者消费产品：" + di.toString());
                System.out.println("产品队列中信息：" + dq.toString());
                System.out.println();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
