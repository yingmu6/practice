package com.csy.interview.written_exam.design_mode.singleton;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class Singleton3 {

    private volatile static Singleton3 singleton;

    private Singleton3() {}

    // 按需实例化
    public static Singleton3 getInstance() {
        if (singleton == null) {
            synchronized (Singleton3.class) {
                if (singleton == null) {
                    singleton = new Singleton3();
                }
            }
        }
        return singleton;
    }
}
