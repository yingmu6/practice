package interview.offer_come.concurrent.queue;

import java.util.concurrent.DelayQueue;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class DelayQueueTest { //@MsY-Doing

    /**
     * 知识点：延迟队列
     *
     * 知识点概要：
     * 1）
     *
     * 问题点答疑：
     * 1）为什么没有输出结果？
     */
    public static void main(String[] args) {
        DelayQueue<DelayData> queue = new DelayQueue<>();
        DelayData delayData = new DelayData();
        delayData.setNumber(11);
        queue.add(delayData);
        while (true) {
            try {
                DelayData data = queue.take();
                System.out.println(data.getNumber());
            } catch (Exception e) {

            }
        }

        /**
         * 输出结果：
         *
         * 结果分析：
         */
    }
}
