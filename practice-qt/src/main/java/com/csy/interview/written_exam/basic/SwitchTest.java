package com.csy.interview.written_exam.basic;

import org.junit.Test;

/**
 * @author chensy
 * @date 2023/8/9
 */
public class SwitchTest { //@MsY-Done

    /**
     * switch使用
     */

    /**
     * 场景1：switch类型
     */
    @Test
    public void test_switch() { //Done
        float a = 0.123f;

        /**
         * 编译报错：
         * Incompatible types. Found: 'float', required: 'char, byte, short, int, Character, Byte, Short, Integer, String, or an enum'
         */
//        switch (a) {
//
//        }

        // 字符串类型
        String b = "xx";
        switch (b) {
            case "xx":
                System.out.println("xx");
                break;
            case "yy":
                System.out.println("yy");
                break;
        }

        // 枚举类型
        Fruit fruit = Fruit.BANANA;
        switch (fruit) {
            case APPLE:
                System.out.println("apple");
                break;
            case BANANA:
                System.out.println("banana");
                //break; // 代码A
            case ORANGE:
                System.out.println("orange");
                break; // 代码B
            default: //若case语句都没匹配上，则执行default语句
                System.out.println("default");
        }

        /**
         * 输出结果：
         * xx
         * banage
         * orange
         *
         * 结果分析：
         * 1）switch中支持的类型有：char, byte, short, int, Character, Byte, Short, Integer, String, or an enum
         *
         * 2）case语句后要加上break，结束当前的case语句，若不加上，会从已匹配的case开始，继续执行后面的case语句（即使case值不匹配）
         *    2.1）若注释掉 代码A，输出结果为 "banana"、"orange"，即没加上break，后面的case语句也执行了
         *
         * 3）case的值都没有匹配上，则执行default语句
         */
    }

    enum Fruit {
        APPLE, BANANA, ORANGE
    }

    /**
     * 场景2：java 12以后switch的新写法（已验证）
     *
     * 切换jdk版本：在project structure中选择按项目、或按模块执行
     * （还要在preferences -> Compiler -> Jdk Compiler中找到指定模块选择jdk）
     *
     */
    @Test
    public void test_new_switch() {
//        int num = 1;
//        switch (num) {
//            case 1,2,3,4,5 -> System.out.println("周内");  //可以省略break语句，从而可以避免漏写break而出错
//            case 6,7 -> System.out.println("周末");
//            default -> System.out.println("非法值");
//        }
    }
}
