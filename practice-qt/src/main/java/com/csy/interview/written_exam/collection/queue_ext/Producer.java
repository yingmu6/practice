package com.csy.interview.written_exam.collection.queue_ext;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @author chensy
 * @date 2023/8/27
 */
public class Producer implements Runnable {

    private BlockingQueue<String> bq;
    private int period = 1000;
    private Random r = new Random();
    private String name;

    public Producer(BlockingQueue<String> bq, int period, String name) {
        this.bq = bq;
        this.period = period;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(period);
                String product = String.valueOf(r.nextInt(100));

                bq.put(product); //插入指定元素到队列中，若队列已满，则阻塞等待
                System.out.println("生产者[" + this.name + "]生产" + product + ", 当前队列中的产品：" + bq);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
