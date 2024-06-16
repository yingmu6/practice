package interview.written_exam.collection.queue_ext;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author chensy
 * @date 2023/8/28
 */
public class MyPriorityBlockingQueuePro implements Runnable {

    private PriorityBlockingQueue<PriorityProduct> pbq;

    private int period = 1000;

    private Random r = new Random();

    public MyPriorityBlockingQueuePro(PriorityBlockingQueue<PriorityProduct> pbq, int period) {
        this.pbq = pbq;
        this.period = period;
    }

    @Override
    public void run() {

        try {
            while (true) {
                Thread.sleep(period);
                if (pbq.size() > 10) {
                    continue;
                }

                PriorityProduct product = new PriorityProduct(r.nextInt(10), "testProduct");
                pbq.offer(product);
                System.out.println("生产产品：" + product + "，产品队列：" + pbq);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
