package com.csy.interview.fourth_book.basic;

import org.junit.Test;

/**
 * @author chensy
 * @date 2023/7/12
 */
public class BitOperationTest { //@MsY-Doing

    /**
     * 知识点：位运算
     *
     * 知识点概要：
     * 1）移位运算：
     *    1.1）左移操作符<< ：将数据转换成二进制数后，向左移若干位，高位丢弃，低位补零
     *    1.2）右移操作符>> ：将数据转换成二进制数后，向右移若干位，高位补符号位，低位丢弃
     *
     * 参考链接：
     * a）https://juejin.cn/post/6844904025880526861 Java的移位运算符
     */

    /**
     * 场景1：左移运算
     */
    @Test
    public void test_left_move() {
        int a = 3;
        System.out.println(a << 4);

        /**
         * 输出结果：
         * 48
         *
         * 结果分析：
         * 将3转换为2进制表示，因为一个int占4个字节，一个字节8位
         * 1）3对应的二进制数为：00000000000000000000000000000011
         * 2）3左移4位的二进制数为：00000000000000000000000000110000 (即对应的10进制数为48)
         */
    }

    /**
     * 场景2：打印int的二进制形式
     *
     * 1）int类型占4个字节，一个字节8位，int共占32位。java中的int是无符号的（c语言还区分int整形和unsigned int无符号整型），取值范围 -2^31~2^31-1。
     * 2）二进制数在内存中以补码的形式存储的
     *   正数：补码就是转化为二进制
     *   负数：补码符号位是1，其它位是对应正数的二进制的取反加一
     *
     */
    @Test
    public void test_print() {
        int num = 48;
        for(int i = 31; i >= 0; i--) {
            System.out.print((num & 1 << i) == 0 ? "0":"1");
        }
    }

    /**
     * 场景2：按位或
     *
     * 验证：(srcIdx | srcSize | srcBytes.length - srcIdx - srcSize) < 0 只要有一个位负数就为负数
     */


    /**
     * 场景3：特定运算的结果
     */
    @Test
    public void test_special_opt() {
        int a = 1 << 13;
        System.out.println(a);

        /**
         * 输出结果：
         * 8192
         *
         * 结果分析：
         * 左高右低，往做移动一位就*2，移动13位，就是2^13次方
         */
    }

}
