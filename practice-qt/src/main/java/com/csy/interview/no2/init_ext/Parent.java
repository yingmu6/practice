package com.csy.interview.no2.init_ext;

/**
 * @author chensy
 * @date 2023/7/24
 */
public class Parent {
    static {
        System.out.println("父类静态块");
    }

    {
        System.out.println("父类非静态块");
    }

    public Parent() {
        System.out.println("父类构造方法");
    }
}
