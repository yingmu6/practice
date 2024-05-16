package com.csy.interview.offer_come.concurrent.thread;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class NewThread2 extends Thread {

    @Override
    public void run() {
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("NewThread2 run()");
    }
}
