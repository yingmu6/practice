package com.csy.interview.offer_come.design_mode.template;

/**
 * @author chensy
 * @date 2024/3/15
 */
public abstract class AbstractTemplate {

    public void templateMethod() {
        checkNumber();
        queueUp();
        handleBusiness();
        serviceEvaluation();
    }

    public void checkNumber() {
        System.out.println("checkNumber......");
    }

    public void queueUp() {
        System.out.println("queue up......");
    }

    public abstract void handleBusiness();

    public void serviceEvaluation() {
        System.out.println("business finished, service evaluation......");
    }
}
