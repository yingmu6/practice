package com.csy.interview.written_exam.basic;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * 变量累加测试（如i++、++i）
 *
 * @author chensy
 * @date 2023/6/7
 */
public class VarCalculateTest { //@MsY-Done

    /**
     * 变量累加
     * 1）i++说明：等价于i=i+1，变量值加1，表达式为加之前的值
     * 2）++i说明：等价于i=i+1，变量值加1，表达式为加之前的值
     *
     * 形象记忆：看变量的位置，变量在前面，表达式的值为，累加之前的值；变量在后面，就是累加之后的值 （***看变量位置***）
     * 处理方式：区分开是变量的值，还是表达式的值。然后根据 "原始值"、"变量值"、"表达式值"的形式来分析
     */

    /**
     * 场景1：基本累加
     */
    @Test
    public void test_basic() { //Done
        int i = 1;
        System.out.println(i++); //输出的值为：1，即累加之前的值

        int j = 1;
        System.out.println(++j); //输出的值为：2，即累加之后的值

        /**
         * 输出结果：
         * 1
         * 2
         *
         * 结果分析：
         * 1）i++：变量在前，即表达式的值为累加之前的值
         * 2）++j：变量在后，即表达式的值为累加之后的值
         */
    }

    /**
     * 场景2：累加运算
     */
    @Test
    public void test_increase() { //Done
        int i = 1;
        System.out.println(i++ + i++); //先计算"+"左边值，i++为1，此时i为2，"+"右边值为2
        System.out.println("i=" + i);
        System.out.println(++i + ++i); //"+"左边值为4，右边值为5
        System.out.println("i=" + i);
        System.out.println(i++ + i++ + i++); // 表达式等价于：5 + 6 + 7（从左到右依次计算）
        System.out.println("i=" + i);

        /**
         * 输出结果：
         * 3
         * i=3
         * 9
         * i=5
         * 18
         * i=8
         *
         * 结果分析：
         * 主要分析出变量与表达式的值
         * 1）因为i++为1，此时变量i=2，i++为2，所以表达式为1+2=3
         * 2）不管++在变量前后，变量值都会自增1，做了两次运算，所以就加了2
         * 3）此时变量为3，而++i为4，第二个++i值为5，所以表达式值为4+5=9
         * 4）变量自增操作了2次，所以结果为3+1+1=5
         * 5）此时变量为5，第一个i++值为5，第二个i++值为6，第三个i++值为7，所以最终表达式为5+6+7=18
         * 6）变量再次操作了3次，所以结果为5+1+1+1=8
         */
    }

    static {
        int x = 5; //局部变量（代码块中的变量为局部变量）
    }

    static int x,y; //成员变量，初始值为0

    /**
     * 场景3：混合运算
     */
    @Test
    public void test_calculate_sum() { //Done

        System.out.println("No1：x = " + x + ", y = " + y); //输出的值为：x=0，y=0

        x--; //此处变量x的值为-1，表达式的为：0
        System.out.println("No2: x = " + x + ", y = " + y); //输出的值为：x=-1，x-- =0
        myMethod();
        System.out.println("No4: x + y++ + x = " + (x + y++ + x)); //输出结果为：(1 + 0++ + 1) = 2

        /**
         * 输出结果：
         * No1：x = 0, y = 0
         * No2: x = -1, y = 0
         * No3: x = 1, y = 0
         * No4: x + y++ + x = 2
         *
         * 结果分析：
         * 1）static变量，若没设值，默认为0
         * 2）x++：变量在前，为加之前的值
         * 3）参与混合运算时，需要明确运算顺序、变量值、表达式值
         */
    }

    @Test
    public void test_calculate_sum2() { //Done
        int x = 1, y = 2, z = 3;
        System.out.println(y+=z--/++x);

        /**
         * 输出结果：
         * 3
         *
         * 结果分析：
         * 1）先将表达式进行转换，即y = y + (z-- / ++x)
         * 2）将值带入计算，即y = 2 + (y / z) = 3
         */
    }

    private void myMethod() {
        y = x++ + ++x; //x累加了2次，即 x = -1 + 2 = 1，y= (-1)++ + (++0) = -1 + 1 = 0
        System.out.println("No3: x = " + x + ", y = " + y); //输出的值为：x=1，y=0
    }

    /**
     * 场景3：进制值输出
     */
    @Test
    public void test_val() { //Done
        System.out.println(0xE0);
        System.out.println(0xF0);
        System.out.println(0x7fff);
        /**
         * 输出结果：
         * 224
         * 240
         * 32767
         *
         * 结果分析：
         * 1）0xE0：因为E为14，所以计算：14*16 + 0*1 = 224
         * 2）0x7fff：计算等式=7*16^3 + 15*16^2 + 15*16^1 + 15*16^0
         */
    }

    /**
     *场景4：money计算
     */
    @Test
    public void test_money() { //Done
        double d1 = 2.55;
        double d2 = 1.20;
        System.out.println("double：2.55- 1.20 = " + (d1 - d2)); //精度不准确

        BigDecimal d3 = new BigDecimal("2.55");
        BigDecimal d4 = new BigDecimal("1.20");
        System.out.println("BigDecimal(String)：2.55 - 1.20 = " + d3.subtract(d4)); //计算精度准确

        BigDecimal amount3 = new BigDecimal(2.55); //若类型为double，内部还是以double内省计算的，计算结果也不准确
        BigDecimal amount4 = new BigDecimal(1.20);
        System.out.println("dBigDecimal(double)：2.55 - 1.20 =" + (amount3.subtract(amount4))); //计算精度不准确

        /**
         * 输出结果：
         * double：2.55- 1.20 = 1.3499999999999999
         * BigDecimal(String)：2.55 - 1.20 = 1.35
         * dBigDecimal(double)：2.55 - 1.20 = 1.3499999999999998667732370449812151491641998291015625
         *
         * 结果分析：
         * 1）使用BigDecimal来表示Money，因为float、double只是计算了一个近似值，无法表示非常精准的值
         * 2）构造BigDecimal时，使用字符串构造，内部会依次取字符来精确计算的。若按double类型构造，内部会按double类型计算，也不准确
         */
    }
}
