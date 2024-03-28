package com.csy.interview.written_exam.basic.polymor_ext;

/**
 * @author chensy
 * @date 2023/7/6
 */
public class Base {
    int i; //成员变量，初始值为0
    public Base() {
        add(1); //调用同名方法时，若子类存在该方法，则调用子类的方法
        System.out.println(i);
    }

    public void add(int v) {
        i += v;
        System.out.println(i);
    }

    public void print() {
        System.out.println(i);
    }
}
