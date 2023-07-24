package com.csy.interview.no2;

import com.csy.interview.no2.init_ext.Derived;
import netscape.security.UserTarget;
import org.junit.Test;

/**
 * @author chensy
 * @date 2023/7/24
 */
public class InitOrderTest {

    /**
     * 程序初始化顺序_测试
     */

    /**
     * 场景1：父类、子类的初始化顺序
     */
    @Test
    public void test_init_order() {
        new Derived();

        /**
         * 输出结果：
         * Basic static block
         * Derived static block
         * Base block
         * Base constructor
         * Derived block
         * Derived constructor
         *
         * 结果分析：
         * 1）先初始化静态块：父类的静态块 -> 子类的静态块
         * 2）再初始化非静态块：
         *    a）父类的非静态块 -> 父类的构造方法
         *    b）子类的非静态块 -> 子类的构造方法
         */
    }

    static {a = 2;} //静态块中初始化
    static int a = 1; //成员域中初始化（从上到下加载，以最后一次初始化为最终的）
    static int b = 3;

    static {b = 4;}

    {c = 5;} //可以放在声明的上面
    int c = 3;

    /**
     * 场景2：块、成员域中初始化顺序
     */
    @Test
    public void test_order() {
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        /**
         * 输出结果：
         * 1
         * 4
         * 3
         *
         * 结果分析：
         * 块中的初始化与成员域中的初始化是平级的，
         * 所以会按照从上到下初始化，最后一次初始化为最终的值
         */
    }
}
