package com.csy.interview.offer_come.design_mode.mediator;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class ColleagueLandlord extends Colleague {

    @Override
    public boolean operation(String message) {
        System.out.println("landlord receive a message from mediator:" + message);
        return true;
    }
}
