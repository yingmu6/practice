package com.csy.interview.offer_come.design_mode.mediator;

/**
 * @author chensy
 * @date 2024/3/16
 */
public abstract class Colleague {

    protected Mediator mediator;

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public abstract boolean operation(String message);
}
