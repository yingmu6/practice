package interview.written_exam.thread.deadlock;

/**
 * @author chensy
 * @date 2023/9/24
 */
public class T1 implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println("Thread t1 is started");
            synchronized (DeadLockTest.obj1) {
                System.out.println("Thread t1 lock obj1");
                Thread.sleep(5000);
                synchronized (DeadLockTest.obj2) {
                    System.out.println("Thread t1 lock obj2");
                }
                System.out.println("Thread t1 is stopped");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
