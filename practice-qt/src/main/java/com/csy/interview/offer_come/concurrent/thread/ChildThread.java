package com.csy.interview.offer_come.concurrent.thread;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class ChildThread implements Runnable {
    @Override
    public void run() {
        System.out.println("create a thread by implements Runnable ");
    }
}
