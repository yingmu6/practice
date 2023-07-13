package com.csy.interview.no1;

import org.junit.Test;

/**
 * 变量累加测试（如i++、++i）
 *
 * @author chensy
 * @date 2023/6/7
 */
public class VarCalculateTest {

    /**
     * 变量累加
     * 1）i++说明：等价于i=i+1，变量值加1，表达式为加之前的值
     * 2）++i说明：等价于i=i+1，变量值加1，表达式为加之前的值
     *
     * 形象记忆：看字符的位置，字符在前面，表达式的值为，累加之前的值；字符在后面，就是累加之后的值
     * 处理方式：区分开是变量的值，还是表达式的值。然后根据 "原始值"、"变量值"、"表达式值"的形式来分析
     */

    /**
     * 场景1：基本累加
     */
    @Test
    public void test_basic() {
        int i = 1;
        System.out.println(i++); //输出的值为：1，即累加之前的值

        int j = 1;
        System.out.println(++j); //输出的值为：2，即累加之后的值
    }

    /**
     * 场景2：混合运算
     */
    static {
        int x = 5; //局部变量（代码块中的变量为局部变量）
    }

    static int x,y; //成员变量，初始值为0
    @Test
    public void test_calculate_sum() {
        System.out.println("No1：x=" + x + ", y=" + y); //输出的值为：x=0，y=0

        x--; //此处变量x的值为-1，表达式的为：0
        System.out.println("No2: x=" + x + ", x-- =" + y); //输出的值为：x=-1，x-- =0

        myMethod();

        System.out.println(x + y++ + x); //输出结果为：(1 + 0++ + 1) = 2
    }

    private void myMethod() {
        y = x++ + ++x; //x累加了2次，即 x = -1 + 2 = 1，y= (-1)++ + (++0) = -1 + 1 = 0
        System.out.println("No3: x=" + x + ", y=" + y); //输出的值为：x=1，y=0
    }

    /**
     * 场景3：进制值输出
     */
    @Test
    public void test_val() {
        System.out.println(0xE0); //输出值为224，因为E位14，所以计算：14*16+0*1 = 224
        System.out.println(0xF0); //输出值为240
    }
}
