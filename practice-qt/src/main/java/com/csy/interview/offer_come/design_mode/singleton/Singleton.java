package com.csy.interview.offer_come.design_mode.singleton;

/**
 * @author chensy
 * @date 2024/3/14
 */
public class Singleton {
    /**
     * 静态内部类
     */
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    private Singleton() {

    }

    public static final Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
