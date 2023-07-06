package com.csy.interview.no1.polymor_ext;

/**
 * @author chensy
 * @date 2023/7/6
 */
public class MyBase extends Base {
    public MyBase() { //先执行父类构造方法
        add(2);
    }

    public void add(int v) {
        i += v * 2;
        System.out.println(i);
    }
}
