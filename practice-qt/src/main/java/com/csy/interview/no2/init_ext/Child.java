package com.csy.interview.no2.init_ext;

/**
 * @author chensy
 * @date 2023/7/24
 */
public class Child extends Parent {

    static {
        System.out.println("子类静态块");
    }

    {
        System.out.println("子类非静态块");
    }

    public Child() {
        System.out.println("子类构造方法");
    }
}
