package com.csy.interview.no2.init_ext;

/**
 * @author chensy
 * @date 2023/7/24
 */
public class Base {
    static {
        System.out.println("Basic static block");
    }

    {
        System.out.println("Base block");
    }

    public Base() {
        System.out.println("Base constructor");
    }
}
