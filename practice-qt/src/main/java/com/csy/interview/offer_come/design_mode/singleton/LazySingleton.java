package com.csy.interview.offer_come.design_mode.singleton;

/**
 * @author chensy
 * @date 2024/3/14
 */
public class LazySingleton {
    /**
     * 懒汉模式（线程安全）
     */
    private static LazySingleton instance;

    private LazySingleton(){};

    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
