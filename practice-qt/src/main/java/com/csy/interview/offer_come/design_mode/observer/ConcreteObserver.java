package com.csy.interview.offer_come.design_mode.observer;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class ConcreteObserver implements Observer {
    @Override
    public void dataChange(String message) {
        System.out.println("receive message:" + message);
    }
}
