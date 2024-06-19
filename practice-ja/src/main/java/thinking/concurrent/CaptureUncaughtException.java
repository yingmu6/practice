package thinking.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class CaptureUncaughtException {

    static class ExceptionThread2 implements Runnable {
        @Override
        public void run() {
            Thread t = Thread.currentThread();
            System.out.println("run() by " + t);
            System.out.println("eh = " + t.getUncaughtExceptionHandler());
            throw new RuntimeException();
        }
    }

    static class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("caught " + e);
        }
    }

    static class HandlerThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            System.out.println(this + " creating new Thread");
            Thread t = new Thread(r);
            System.out.println("created " + t);
            t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
            System.out.println("eh = " + t.getUncaughtExceptionHandler());
            return t;
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool(
                new HandlerThreadFactory());
        exec.execute(new ExceptionThread2());
    }
}
