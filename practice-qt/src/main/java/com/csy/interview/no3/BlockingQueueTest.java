package com.csy.interview.no3;

import com.csy.interview.no3.queue_ext.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * @author chensy
 * @date 2023/8/27
 */
public class BlockingQueueTest {

    /**
     * 阻塞队列_测试
     * 1）当阻塞队列是空时，从队列中获取元素的操作将会被阻塞
     *    当阻塞队列是满时，往队列中添加元素的操作将会被阻塞
     *   （为什么需要使用BlockingQueue？好处是我们不需要关心什么时候需要阻塞线程，什么时候需要唤醒线程，因为BlockingQueue都一手给你包办好了。
     *    在concurrent包发布以前，多线程环境下，我们每个程序员都必须自己去控制这些细节，尤其还要兼顾效率和线程安全，而这会给我们的程序带来不小的复杂度。)
     *
     * 2）阻塞队列有主要以下几种：
     *    ArrayBlockingQueue: 由数组结构组成的有界阻塞队列
     *    LinkedBlockingDeque: 由链表结构组成的有界(但大小默认值Integer>MAX_VALUE)阻塞队列
     *    PriorityBlockingQueue:支持优先级排序的无界阻塞队列
     *    DelayQueue: 使用优先级队列实现的延迟无界阻塞队列
     *    SynchronousQueue:不存储元素的阻塞队列,也即是单个元素的队列
     *    LinkedTransferQueue:由链表结构组成的无界阻塞队列
     *    LinkedBlockingDeque:由了解结构组成的双向阻塞队列
     *
     * 3）使用阻塞队列来解决生产者/消费者问题的时候，不再需要进行同步处理，这种思想在消息队列中有着广泛的应用。
     *
     * 4）由于生产者与消费者是两个独立的并发体，他们之间是用缓冲区作为桥梁连接，生产者只需要往缓冲区里丢数据，就可以继续生产下一个数据，而消费者只需要从缓冲区了拿数据即可，这样就不会因为彼此的处理速度而发生阻塞。
     *
     * 参考链接：
     * a）https://bbs.huaweicloud.com/blogs/345349 生产者消费者模型概念以及优点
     * b）https://blog.csdn.net/qq9808/article/details/105741599 阻塞队列的使用以及用途
     */

    /**
     * 场景1：使用ArrayBlockingQueue实现生产者、消费者模型
     */
    @Test
    public void test_producer_consumer_by_ArrayBlockingQueue() throws IOException {
        BlockingQueue<String> abq = new ArrayBlockingQueue<>(5);

        ExecutorService pool = Executors.newCachedThreadPool();
        pool.execute(new Producer(abq, 1000, "生产者"));
        pool.execute(new Consumer(abq, 5000, "消费者1"));
        pool.execute(new Consumer(abq, 5000, "消费者2"));
        pool.shutdown();

        System.in.read(); //需要加上这句代码，持续的生产、消费

        /**
         * 输出结果：
         * 生产者[生产者]生产25, 当前队列中的产品：[25]
         * 生产者[生产者]生产54, 当前队列中的产品：[25, 54]
         * 生产者[生产者]生产73, 当前队列中的产品：[25, 54, 73]
         * 生产者[生产者]生产76, 当前队列中的产品：[25, 54, 73, 76]
         * 消费者[消费者1]消费25, 当前队列中的产品: [54, 73, 76]
         * 消费者[消费者2]消费54, 当前队列中的产品: [73, 76]
         * 生产者[生产者]生产6, 当前队列中的产品：[73, 76, 6]
         * ...持续的生产、消费...
         *
         * 结果分析：
         * 1）从运行结果看，生产者：生产产品，产品在队列中增加，而消费者：消费产品，产品在队列中移除
         */
    }

    /**
     * 场景2：使用PriorityBlockingQueue（优先级阻塞队列），实现生产者和消费者
     */
    @Test
    public void test_producer_consumer_by_PriorityBlockingQueue() throws IOException {
        PriorityBlockingQueue<PriorityProduct> pbq = new PriorityBlockingQueue<>();

        ExecutorService es = Executors.newFixedThreadPool(2);
        es.execute(new MyPriorityBlockingQueuePro(pbq, 1000));
        es.execute(new MyPriorityBlockingQueueCus(pbq, 5000));
        es.shutdown();

        System.in.read();

        /**
         * 输出结果：
         * 生产产品：{priority:7,name=testProduct，产品队列：[{priority:7,name=testProduct]
         * 生产产品：{priority:4,name=testProduct，产品队列：[{priority:7,name=testProduct, {priority:4,name=testProduct]
         * 生产产品：{priority:2,name=testProduct，产品队列：[{priority:7,name=testProduct, {priority:4,name=testProduct, {priority:2,name=testProduct]
         * 生产产品：{priority:1,name=testProduct，产品队列：[{priority:7,name=testProduct, {priority:4,name=testProduct, {priority:2,name=testProduct, {priority:1,name=testProduct]
         * 消费产品：{priority:7,name=testProduct，产品队列：[{priority:4,name=testProduct, {priority:1,name=testProduct, {priority:2,name=testProduct]
         * 生产产品：{priority:7,name=testProduct，产品队列：[{priority:7,name=testProduct, {priority:4,name=testProduct, {priority:2,name=testProduct, {priority:1,name=testProduct]
         * 生产产品：{priority:3,name=testProduct，产品队列：[{priority:7,name=testProduct, {priority:4,name=testProduct, {priority:2,name=testProduct, {priority:1,name=testProduct, {priority:3,name=testProduct]
         * 生产产品：{priority:5,name=testProduct，产品队列：[{priority:7,name=testProduct, {priority:4,name=testProduct, {priority:5,name=testProduct, {priority:1,name=testProduct, {priority:3,name=testProduct, {priority:2,name=testProduct]
         * 生产产品：{priority:8,name=testProduct，产品队列：[{priority:8,name=testProduct, {priority:4,name=testProduct, {priority:7,name=testProduct, {priority:1,name=testProduct, {priority:3,name=testProduct, {priority:2,name=testProduct, {priority:5,name=testProduct]
         * ...持续生产、消费...
         *
         * 结果分析：
         * 1）消费产品，按优先级大小消费队列中的产品
         * 2）看源码PriorityBlockingQueue#take时，取比较器来比较元素
         */
    }

    /**
     * 场景3：测试延迟队列
     */
    @Test
    public void test_delay_queue() throws IOException {
        DelayQueue<MyDelayItem> dq = new DelayQueue<>();
        ExecutorService es = Executors.newFixedThreadPool(5);
        es.execute(new MyDelayPro(dq, 6));
        es.execute(new MyDelayCus(dq));
        es.shutdown();

        System.in.read();

        /**
         * 输出结果：
         * 生产者把产品加入队列：{存活时间：7，出队时间：1693530921054} size：1
         *
         * 消费者消费产品：{存活时间：7，出队时间：1693530921054}
         * 产品队列消息：[]
         *
         * 生产者把产品加入队列：{存活时间：1，出队时间：1693530922053} size：1
         *
         * 生产者把产品加入队列：{存活时间：1，出队时间：1693530923056} size：2
         *
         * 消费者消费产品：{存活时间：1，出队时间：1693530922053}
         * 产品队列消息：[{存活时间：1，出队时间：1693530923056}]
         *
         * ...持续生产、消费...
         *
         *
         * 结果分析：
         * 1）查看jDelayQueue#offer插入元素、take获取元素的源码可知
         *    a）offer插入元素：普通的插入元素逻辑
         *    b）take获取元素：取队首元素时，会判断元素的getDelay是否小于或等于0，即是否已经过期了，过期了的元素才能被获取
         *
         * 2）由运行结果可知：消费端消费的队首元素，需要元素的getDelay() <= 0，即已过期了，才能消费
         */
    }

    /**
     * 场景4：测试延迟队列
     */
    @Test
    public void test_delay_queue_v2() {
        DelayQueue<DelayData> queue = new DelayQueue<>();
        DelayData data = new DelayData();
        data.setNumber(33);
        queue.add(data);
        while (true) {
            try {
                long currentTime = System.currentTimeMillis();
                DelayData delay = queue.take();
                System.out.println("获取到数据：" + delay.getNumber() + ", 耗时：" + (System.currentTimeMillis() - currentTime) / 1000);
            } catch (Exception e) {
            }
        }

        /** todo @Ms-01-20
         * 输出结果：
         *
         * 结果分析：
         *
         * 问题点答疑：
         * 1）为什么没有结果输出？
         */
    }

    public class DelayData implements Delayed {

        private Integer number;

        private long delayTime = 1000;

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return this.delayTime;
        }

        @Override
        public int compareTo(Delayed o) {
            DelayData compare = (DelayData) o;
            return this.number.compareTo(compare.getNumber());
        }
    }
}
