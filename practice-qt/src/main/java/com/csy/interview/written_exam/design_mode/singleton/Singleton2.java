package com.csy.interview.written_exam.design_mode.singleton;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class Singleton2 {

    // 在类加载的时候直接初始化静态变量
    private static Singleton2 instance = new Singleton2();

    private Singleton2() {}

    public static synchronized Singleton2 getInstance() {
        return instance;
    }
}
