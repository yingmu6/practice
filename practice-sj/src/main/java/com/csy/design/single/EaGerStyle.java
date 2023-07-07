package com.csy.design.single;

/**
 * @author chensy
 * @date 2023/7/7
 */
public class EaGerStyle {
    /**
     * 饿汉式模式
     * （即通过静态成员变量表示，在类加载时就创建）
     */

    private static EaGerStyle instance = new EaGerStyle();

    private EaGerStyle() {}

    public static EaGerStyle getInstance() {
        return instance;
    }
}
