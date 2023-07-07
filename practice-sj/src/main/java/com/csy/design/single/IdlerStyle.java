package com.csy.design.single;

/**
 * @author chensy
 * @date 2023/7/7
 */
public class IdlerStyle {

    /**
     * 懒汉模式（需要使用时创建实例）
     * 1）非线程安全
     * 2）线程安全（加上synchronized加锁）
     */

    private static IdlerStyle instance;

    private IdlerStyle() {}

    public static IdlerStyle getInstance() { //非线程安全
        if (instance == null) {
            instance = new IdlerStyle();
        }
        return instance;
    }

    public static synchronized IdlerStyle getInstanceV2() { //线程安全（但是每次进入方法时，都会加锁处理，性能下降）
        if (instance == null) {
            instance = new IdlerStyle();
        }
        return instance;
    }
}
