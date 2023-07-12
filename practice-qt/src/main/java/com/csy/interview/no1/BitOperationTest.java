package com.csy.interview.no1;

import org.junit.Test;

/**
 * @author chensy
 * @date 2023/7/12
 */
public class BitOperationTest {

    /**
     * 位运算_测试
     */

    /**
     * 场景1：左移运算
     *
     * 参考链接：
     * a）https://juejin.cn/post/6844904025880526861
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
}
