package interview.written_exam.collection.queue_ext;

import java.util.concurrent.BlockingQueue;

/**
 * @author chensy
 * @date 2023/8/27
 */
public class Consumer implements Runnable {

    private BlockingQueue<String> bq;
    private int period = 1000;
    private String name;

    public Consumer(BlockingQueue<String> bq, int period, String name) {
        this.bq = bq;
        this.period = period;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(period);
                String value = bq.take(); //取回并移除队列首部元素（若队列没有元素，则阻塞等待）
                System.out.println("消费者[" + this.name + "]消费" + value + ", 当前队列中的产品: " + bq.toString());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
