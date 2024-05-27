package com.csy.interview.written_exam.basic.reflect_ext;

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

    B b = new B();

    public A () {
        System.out.println("reflect_ext.A()");
    }
}
