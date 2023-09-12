package com.csy.interview.no2.thread_ext;

/**
 * @author chensy
 * @date 2023/9/12
 */
public class ErrHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) { //线程异常时，会调用该方法。可在在该方法中做日志记录，以及释放系统资源等
        System.out.println("This is:" + t.getName() + ",Message:" + e.getMessage());
        e.printStackTrace();
        ((ThreadException)t).simulateCleanup();
    }
}
