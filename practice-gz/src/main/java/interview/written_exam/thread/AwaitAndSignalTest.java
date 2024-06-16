package interview.written_exam.thread;

import org.junit.Test;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chensy
 * @date 2023/9/9
 */
public class AwaitAndSignalTest {

    /**
     * await和signal_测试
     * 1）ReentrantLock借助Condition可以完成等待/通知模型，Condition中的await()对应Object的wait()，signal()对应notify()。
     *
     * 2）Condition依赖Lock接口，通过Lock产生Condition，如：lock.newCondition()。
     *
     * 3）Condition的await()和signal()必须要在Lock保护之内。
     *
     * 4）一个Lock可以创建多个Condition实例，实现多路通知。
     *
     * 5）使用notify()方法进行通知时，被选择的线程是在java虚拟机中随机选择的。而使用ReentrantLock+Condition的方式可以实现有选择的通知
     *
     * 参考链接：
     * a）http://www.javabyexamples.com/lock-and-condition-in-java
     */

    /**
     * 场景1：await、signal基本使用
     */
    @Test
    public void test_thread_await_signal() throws IOException {
        Buffer buffer = new Buffer();
        Producer producer = new Producer(buffer); //对同一个Buffer对象处理
        Consumer consumer = new Consumer(buffer);

        for (int i = 0; i < 2; i++) {
            new Thread(producer, "生产者-" + i).start();
        }

        for (int i = 0; i < 3; i++) {
            new Thread(consumer, "消费者-" + i).start();
        }

        System.in.read();

        /**
         * 输出结果：
         * 消费者-0队列为空，消费者被阻塞
         * 生成数字：队列中的内容为：[555584592]
         * 生成数字：队列中的内容为：[-1881869907, 555584592]
         * 生成数字：队列中的内容为：[-1881869907, 555584592, 1616009519]
         * 生成数字：队列中的内容为：[-1881869907, -552786175, 1616009519, 555584592]
         * 生成数字：队列中的内容为：[-1881869907, -716678636, 1616009519, 555584592, -552786175]
         * 消费者-1消费的数字为：-1881869907
         * 消费者-1消费的数字为：-716678636
         * 消费者-1消费的数字为：-552786175
         * ...持续的生产、消费数字...
         *
         * 结果分析：
         * 1）Buffer中提供生产、消费使用的队列，并且创建了生产者、消费者对应的Condition，由Condition实现 等待await()、通知signal()
         *
         * 结果总结：
         * 1）若await()、signal()没有放在lock范围内，会报IllegalMonitorStateException异常（将lock.lock()地方注释掉，即可看到异常）
         */
    }

    class Buffer {
        private final Lock lock = new ReentrantLock();

        private final Condition producerCondition = lock.newCondition(); //生产线程对应的Condition（创建与锁实例绑定的Condition）

        private final Condition consumerCondition = lock.newCondition(); //消费线程对应的Condition
        private final int BUFFER_SIZE = 10;

        private PriorityQueue<Integer> queue = new PriorityQueue<>(BUFFER_SIZE); //创建优先级队列

        public void put() { //放入对象（方法会被生产者调用）
            Random random = new Random();
            lock.lock(); //必须先获得锁（await()、signal()必须要在lock的保护之内）
            try {
                while (queue.size() == BUFFER_SIZE) { //对于生产者来说，队列已满时，需要阻塞
                    System.out.println(Thread.currentThread().getName() + "队列已满，生产者被阻塞");
                    producerCondition.await(); //阻塞生产线程
                }
                queue.add(random.nextInt()); //继续生产
                System.out.println("生成数字：队列中的内容为：" + queue);
                Thread.sleep(1000);
                consumerCondition.signal(); //唤醒消费线程
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void take() { //获取对象（方法会被消费者调用）
            lock.lock();
            try {
                while (queue.size() == 0) { //对于消费而言，队列为空时，需要阻塞
                    System.out.println(Thread.currentThread().getName() + "队列为空，消费者被阻塞");
                    consumerCondition.await(); //阻塞消费线程
                }
                int d = queue.poll();
                System.out.println(Thread.currentThread().getName() + "消费的数字为：" + d);
                Thread.sleep(1000);
                producerCondition.signalAll(); //唤醒生产线程
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    class Producer implements Runnable {

        private Buffer buffer;

        public Producer(Buffer b) {
            this.buffer = b;
        }

        @Override
        public void run() {
            while (true) {
                buffer.put(); //生产线程，持续生产
            }
        }
    }

    class Consumer implements Runnable {

        private Buffer buffer;

        public Consumer(Buffer b) {
            this.buffer = b;
        }

        @Override
        public void run() {
            while (true) {
                buffer.take(); //消费线程，持续消费
            }
        }
    }
}
