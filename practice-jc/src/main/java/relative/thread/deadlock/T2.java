package relative.thread.deadlock;

/**
 * @author chensy
 * @date 2023/9/24
 */
public class T2 implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println("Thread t2 is started");

            synchronized (DeadLockTest.obj2) {
                System.out.println("Thread t2 lock obj2");
                Thread.sleep(3000);
                synchronized (DeadLockTest.obj1) {
                    System.out.println("Thread t2 lock obj1");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
