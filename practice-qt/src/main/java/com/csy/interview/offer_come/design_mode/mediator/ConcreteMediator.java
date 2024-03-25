package com.csy.interview.offer_come.design_mode.mediator;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class ConcreteMediator extends Mediator {

    public ConcreteMediator(Colleague colleagueTenant, Colleague colleagueLandlord) {
        super(colleagueTenant, colleagueLandlord);
    }

    @Override
    public boolean notifyColleagueTenant(String message) {
        if (colleagueTenant != null) {
            return colleagueTenant.operation(message);
        }
        return false;
    }

    @Override
    public boolean notifyColleagueLandlord(String message) {
        if (colleagueLandlord != null) {
            return colleagueLandlord.operation(message);
        }
        return false;
    }

    public static void main(String[] args) {
        Colleague colleagueTenant = new ColleagueTenant();
        Colleague colleagueLandlord = new ColleagueLandlord();
        ConcreteMediator concreteMediator = new ConcreteMediator(colleagueTenant, colleagueLandlord);
        boolean result = concreteMediator.notifyColleagueTenant("想租大点面积的房子吗？");
        if (result) {
            concreteMediator.notifyColleagueLandlord("租客对面积满意");
        } else {
            concreteMediator.notifyColleagueLandlord("租客对面积不满意");
        }

        /**
         * 输出结果：
         * tenant receive a message from mediator:想租大点面积的房子吗？
         * landlord receive a message from mediator:租客对面积满意
         *
         * 结果分析：
         *
         * 问题点答疑：
         */
    }
}
