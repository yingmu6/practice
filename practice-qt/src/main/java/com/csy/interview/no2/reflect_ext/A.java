package com.csy.interview.no2.reflect_ext;

/**
 * @author chensy
 * @date 2023/7/24
 */
public class A {

    static { //静态块
        System.out.println("static block");
    }

    {       //动态构造块
        System.out.println("dynamic block");
    }
}
