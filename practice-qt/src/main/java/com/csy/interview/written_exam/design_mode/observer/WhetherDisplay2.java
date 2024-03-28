package com.csy.interview.written_exam.design_mode.observer;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class WhetherDisplay2 implements Observer {

    private float temprature;

    public WhetherDisplay2(Subject whether) {
        whether.registerObserver(this);
    }

    @Override
    public void update(float temp) {
        this.temprature = temp;
        display();

    }

    public void display() {
        System.out.println("display2-----" + this.temprature);
    }
}
