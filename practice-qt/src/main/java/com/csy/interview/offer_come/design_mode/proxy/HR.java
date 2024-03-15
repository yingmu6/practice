package com.csy.interview.offer_come.design_mode.proxy;

/**
 * @author chensy
 * @date 2024/3/14
 */
public class HR implements Company {
    @Override
    public void findWorker(String title) {
        System.out.println("i need find a worker, title is:" + title);
    }
}
