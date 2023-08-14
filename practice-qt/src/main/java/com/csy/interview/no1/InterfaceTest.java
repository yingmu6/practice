package com.csy.interview.no1;

/**
 * @author chensy
 * @date 2023/8/9
 */
public interface InterfaceTest {

    /**
     * 接口中声明的内容
     * 1）变量为public static final，可以不写出修饰
     * 2）方法为public abstract，可以不写出修饰
     * 3）默认方法、静态默认方法都有实现体
     * 4）外部的接口，如InterfaceTest可以用public修饰，但不能用private、protected、static修饰的
     * 5）内部接口，如当前接口中的InnerA，可以使用public、static修饰，但不能用private、protected修饰
     *
     * 默认方法的主要目的：实现接口升级，不影响原有的接口和类
     */

    // 变量声明
    public static final int var1 = 0;
    int var2 = 1;

    // 方法声明
    public abstract void f1();
    void f2();

    // default方法
    default void g() {
        System.out.println("default method !");
    }

    // static默认方法
    static void h() {
        System.out.println("static method !");
    }

    interface innerA { //内部接口：可以使用public、static修饰，但不能用private、protected修饰

    }

}