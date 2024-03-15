package com.csy.interview.offer_come.design_mode.decorator;


/**
 * @author chensy
 * @date 2024/3/14
 */
public class Source implements Sourceable {

    @Override
    public void createComputer() {
        System.out.println("create computer by source");
    }
}
