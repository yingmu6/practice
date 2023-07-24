package com.csy.interview.no2.init_ext;

/**
 * @author chensy
 * @date 2023/7/24
 */
public class Derived extends Base {

    static {
        System.out.println("Derived static block");
    }

    {
        System.out.println("Derived block");
    }

    public Derived() {
        System.out.println("Derived constructor");
    }
}
