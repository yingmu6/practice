package interview.written_exam.thread;

/**
 * @author chensy
 * @date 2023/9/7
 */
public class WaitAndNotifyTest {

    /**
     * 线程wait和notify_测试
     * 1）wait和notify实现多线程之间通信/同步
     *
     * 2）In a multithreaded environment, multiple threads might try to modify the same resource.
     *   Not managing threads properly will of course lead to consistency（一致性） issues.
     *
     * 3）Object.wait() to suspend（暂停、挂起） a thread
     *    Object.notify() to wake（唤醒、唤起） a thread up
     *
     * 参考链接：
     * a）https://www.baeldung.com/java-wait-notify
     */

    private Object lock = new Object();
    private boolean envReady = false;

    private class WorkerThread extends Thread {
        public void run() {
            System.out.println("线程 WorkerThread 等待拿锁");
            synchronized (lock) {
                try {
                    System.out.println("线程 WorkerThread 拿到锁");
                    if (!envReady) { //判断条件是否满足，不满足则等待
                        System.out.println("线程 WorkerThread 放弃锁");
                        lock.wait(); //线程等待，直到另外的线程调用notify()或notifyAll()
                    }
                    System.out.println("线程 WorkerThread 收到通知后继续执行");
                } catch (InterruptedException e) {

                }
            }
        }
    }

    private class PrepareEnvThread extends Thread {
        public void run() {
            System.out.println("线程 PrepareEnvThread 等待拿锁");
            synchronized (lock) {
                System.out.println("线程 PrepareEnvThread 拿到锁");
                envReady = true; //更改条件
                lock.notify(); //线程唤醒（监控对象的其中一个线程将被唤起，随意唤起的）
                System.out.println("通知 WorkerThread");
            }
        }
    }

    public void prepareEnv() {
        new PrepareEnvThread().start();
    }

    public void work() {
        new WorkerThread().start();
    }

    public static void main(String[] args) {
        WaitAndNotifyTest test = new WaitAndNotifyTest();
        test.work();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        test.prepareEnv();
    }

    /**
     * 输出结果：
     * 线程 WorkerThread 等待拿锁
     * 线程 WorkerThread 拿到锁
     * 线程 WorkerThread 放弃锁
     * 线程 PrepareEnvThread 等待拿锁
     * 线程 PrepareEnvThread 拿到锁
     * 通知 WorkerThread
     * 线程 WorkerThread 收到通知后继续执行
     *
     * 结果分析：
     * 1）执行WorkerThread线程时，会判断条件是否满足，不满线程调用wait()进入阻塞状态
     * 2）执行PrepareEnvThread线程时，更改条件，并调用notify()，环境监控对象关联的其中之一的线程，所以WorkerThread从阻塞的地方开始执行（不是又执行一遍run()方法）
     *
     * 结果总结：
     * 1）可以在debug时，看到各个线程的状态
     * 2）wait(time)是在指定时间后，可自行唤醒，而wait()需要其它线程调用notify()或notifyAll()
     * 3）Note that calling wait(0) is the same as calling wait(). wait(0)与wait()是同样的效果
     */
}
