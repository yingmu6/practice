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
     *
     * 参考链接：
     * 1）https://www.jianshu.com/p/cfb7df8d3a8b 按位与、或、非、异或
     */
    @Test
    public void test_calculate() {
       int m = 5, n = 5;
       if ((m != 5) && (n++ == 5)) {} // &&短路运算，第一个表达式为假，则整体计算结果为假，则不再进入第二个表达式
       System.out.println("a." + n);

       m = n = 5;
       if ((m != 5) & (n++ == 6)) {} // &按位与，两边的表达式都会执行
       System.out.println("b." + n);

       m = n = 5;
       if ((m == 5) || (n++ == 5)) {} // ||短路运算，第一个表达为真，则整体计算结果为真，则不再进入第二个表达式
       System.out.println("c." + n);

       m = n = 5;
       if ((m == 5) | (n++ == 5)) {} // |按位或，两边的表达式都会执行
       System.out.println("d." + n);

       int a = 1, b = 2;
       int c = a & b;
       System.out.println("a&b" + c); //按位与，两个都为1，位运算的结果才为1，即0&0=0; 0&1=0; 1&0=0; 1&1=1

       /**
        * 结果分析：
        *
        * 预期输出：
        * a.5
        * b.6
        * c.5
        * d.6
        * a&b0
        *
        * 实际输出：
        * a.5
        * b.6
        * c.5
        * d.6
        * a&b0
        *
        * 原因分析：
        * 预期结果和实际结果相同，原因见上面代码中描述
        */
    }

    /**
     * 场景4：三元表达式中的类型
     */
    @Test
    public void test_ternary_expression() {
        /**
         * Java中的char类型是一种16位的Unicode字符，取值范围为0到65535。 由于char是无符号整数类型，因此不存在负数char值
         */
        char x = 'x'; // 'x' 字符对应的int值为120
        int i = 10;
        System.out.println(false ? i : x);
        System.out.println(false ? 10 : x);
        System.out.println(false ? 65535 : x);
        System.out.println(false ? 65536 : x);

        /**
         * 输出结果：
         * 120
         * x
         * x
         * 120
         *
         * 结果分析：
         * 对于三元表达式：express ？ value1 ： value2
         * 1）若 value1是变量，value2的类型与value1保持一致
         * 2）若 value1是常量，判断value1是否能用value2的类型表示，若能：value2保持原有的类型，若不能，value2的类型要与value1保持一致
         *
         * 1）i是int变量，所以x要变为int的数值
         * 2）10是常量，可以用x对应的char表示，所以x保持原有类型，所以输出x
         * 3）65535是常量，可以用x对应的char表示，所以x保持原有类型，所以输出x
         * 4）65536是常量，但是超出了char的范围了，所以不能用char表示，所以x的值要变为int类型，所以输出120
         */
    }
}
