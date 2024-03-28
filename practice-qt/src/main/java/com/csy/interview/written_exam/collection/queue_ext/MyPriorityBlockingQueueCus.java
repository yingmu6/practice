package com.csy.interview.written_exam.collection.queue_ext;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author chensy
 * @date 2023/8/28
 */
public class MyPriorityBlockingQueueCus implements Runnable {

    private PriorityBlockingQueue<PriorityProduct> pbq;

    private int period = 1000;

    public MyPriorityBlockingQueueCus(PriorityBlockingQueue<PriorityProduct> pbq, int period) {
        this.pbq = pbq;
        this.period = period;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(period);
                PriorityProduct product = pbq.take();
                System.out.println("消费产品：" + product + "，产品队列：" + pbq);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
