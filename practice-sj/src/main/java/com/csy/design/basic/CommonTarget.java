package com.csy.design.basic;

/**
 * @author : chensy
 * Date : 2020-03-01 14:42
 */
public class CommonTarget implements Target {
    @Override
    public void sayHello() {
        // 常用的接口，实现接口接口即可
        System.out.println("你好！");
    }
}
