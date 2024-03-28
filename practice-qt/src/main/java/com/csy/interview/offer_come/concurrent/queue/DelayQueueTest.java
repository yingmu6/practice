package com.csy.interview.offer_come.concurrent.queue;

import java.util.concurrent.DelayQueue;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class DelayQueueTest {

    /**
     * 延迟队列测试
     */
    public static void main(String[] args) {
        DelayQueue<DelayData> queue = new DelayQueue<>();
        queue.add(new DelayData());
        while (true) {
            try {
                DelayData data = queue.take();
            } catch (Exception e) {

            }
        }
    }
}
