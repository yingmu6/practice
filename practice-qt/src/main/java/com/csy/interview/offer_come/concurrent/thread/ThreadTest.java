package com.csy.interview.offer_come.concurrent.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chensy
 * @date 2024/3/23
 */
public class ThreadTest {

    Logger logger = LoggerFactory.getLogger(ThreadTest.class);

    /**
     * 场景1：Thread的创建方式（通过继承Thread类）
     */
    public void createThreadWay1() {

        class NewThread extends Thread {
            @Override
            public void run() {
                logger.info("create a thread by extends Thread");
            }
        }

        NewThread newThread = new NewThread();
        newThread.start();
    }
}
