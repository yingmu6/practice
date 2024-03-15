package com.csy.interview.offer_come.design_mode.observer;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class ConcreteSubject extends Subject {
    @Override
    public void notifyObserver(String message) {
        for(Object obs : observerList) {
            System.out.println("notify observer " + message + "change...");
            ((Observer)obs).dataChange(message);
        }
    }

    public static void main(String[] args) {
        Subject subject = new ConcreteSubject();
        Observer obs = new ConcreteObserver();
        subject.add(obs);
        subject.notifyObserver("data1");

        /**
         * 输出结果：
         * notify observer data1change...
         * receive message:data1
         *
         * 结果分析：
         *
         * 问题点答疑：
         */
    }
}
