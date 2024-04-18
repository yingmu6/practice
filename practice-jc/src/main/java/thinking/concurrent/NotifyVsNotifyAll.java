package thinking.concurrent;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class NotifyVsNotifyAll {
    static class Blocker {
        synchronized void waitingCall() {
            try {
                while (!Thread.interrupted()) {
                    wait();
                    System.out.println(Thread.currentThread() + " ");
                }
            } catch (InterruptedException e) {
                // Do Nothing
            }
        }
        synchronized void prod() { notify(); }
        synchronized void prodAll() { notifyAll(); }
    }

    static class Task implements Runnable {
        static Blocker blocker = new Blocker();
        public void run() { blocker.waitingCall();}
    }

    static class Task2 implements Runnable {
        static Blocker blocker = new Blocker();
        public void run() { blocker.waitingCall(); }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new Task());
        exec.execute(new Task2());
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            boolean prod = true;
            public void run() {
                if (prod) {
                    System.out.println("\n notify() ");
                } else {

                }
            }
        }, 400, 400);
    }
}
