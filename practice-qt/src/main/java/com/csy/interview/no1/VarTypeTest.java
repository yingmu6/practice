package com.csy.interview.no1;

import org.junit.Test;

/**
 * 变量类型
 *
 * @author chensy
 * @date 2023/6/14
 */
public class VarTypeTest {

    /**
     * 场景1：进制表示：
     * 1）在整数前加0，表示八进制
     * 2）在整数前加0x，表示十六进制
     */
    @Test
    public void test_basic() {
        int i = 012;
        int j = 034;
        int k = (int) 056L; //long类型，转换为int，做强制转换
//        int l = 078; //此处编译错误，因为八进制表示的数范围为 0~7
        System.out.println(i);
        System.out.println(j);
        System.out.println(k);

        /**
         * 结果输出：
         * 10
         * 28
         * 46
         *
         * 结果分析：
         * 1）值计算为：(8^1) * 1 + (8^0) * 2 = 8 + 2 = 10
         * 2）值计算为：(8^1) * 3 + (8^0) * 4 = 24 + 4 = 28
         * 3）值计算为：(8^1) * 5 + (8^0) * 6 = 40 + 6 = 46
         */
    }

    /**
     * 场景2：不同类型变量复制
     */
    @Test
    public void test_assign() {
        short s = 1;
//        s = s + 1; //此处 s + 1，经过计算后，变为int类型，所以赋值给short类型时，要做强制转换
        s = (short) (s + 1);
        System.out.println(s);
    }

    /**
     * 场景3：测试 逻辑运算和按位运算
     */
    @Test
    public void test_calculate() { //todo @csy
       int m = 5, n = 5;
       if ((m != 5) && n == 5) {

       }
    }
}
