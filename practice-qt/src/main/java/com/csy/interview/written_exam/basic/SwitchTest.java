package com.csy.interview.written_exam.basic;

import org.junit.Test;

/**
 * @author chensy
 * @date 2023/8/9
 */
public class SwitchTest { //@MsY-Doing

    /**
     * switch使用
     */

    /**
     * 场景1：switch类型
     */
    @Test
    public void test_switch() { //Doing
        float a= 0.123f;

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
        Fruit fruit = Fruit.APPLE;
        switch (fruit) {
            case APPLE:
                System.out.println("apple");
                break; //要有break语句，不然后面的case内容也会执行
            case BANANA:
                System.out.println("banana");
                break;
        }

        /**
         * 输出结果：
         * xx
         * apple
         *
         * 结果分析：
         */
    }

    enum Fruit {
        APPLE, BANANA
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
