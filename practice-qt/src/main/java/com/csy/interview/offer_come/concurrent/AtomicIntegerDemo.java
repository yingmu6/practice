package com.csy.interview.offer_come.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chensy
 * @date 2024/1/19
 */
public class AtomicIntegerDemo implements Runnable {

    public static AtomicInteger safeCounter = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            safeCounter.getAndIncrement();
        }
    }
}
