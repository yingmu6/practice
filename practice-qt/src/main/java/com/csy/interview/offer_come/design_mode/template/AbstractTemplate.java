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

    public static void main(String[] args) {
        AbstractTemplate template1 = new TakeMoney();
        template1.templateMethod();

        AbstractTemplate template2 = new SaveMoney();
        template2.templateMethod();

        /**
         * 输出结果：
         * checkNumber......
         * queue up......
         * take money form bank.
         * business finished, service evaluation......
         * checkNumber......
         * queue up......
         * save money to the bank.
         * business finished, service evaluation......
         *
         * 结果分析：
         *
         * 问题点答疑：
         */
    }
}
