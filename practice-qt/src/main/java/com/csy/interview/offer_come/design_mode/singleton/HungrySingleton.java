package com.csy.interview.offer_come.design_mode.singleton;

/**
 * @author chensy
 * @date 2024/3/14
 */
public class HungrySingleton {
    /**
     * 饿汉模式
     */
    private static HungrySingleton instance = new HungrySingleton();

    private HungrySingleton() {}

    public static HungrySingleton getInstance() {
        return instance;
    }

}
