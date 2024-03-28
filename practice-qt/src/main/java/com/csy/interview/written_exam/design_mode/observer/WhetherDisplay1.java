package com.csy.interview.written_exam.design_mode.observer;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class WhetherDisplay1 implements Observer {

    private float temprature;

    public WhetherDisplay1(Subject whether) {
        whether.registerObserver(this);
    }

    @Override
    public void update(float temp) {
        this.temprature = temp;
        display();
    }

    public void display() {
        System.out.println("display1***:" + this.temprature);
    }
}
