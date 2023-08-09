package com.csy.interview.no1;

import org.junit.Test;

import static java.lang.Math.sqrt;

/**
 * @author chensy
 * @date 2023/6/30
 */
public class CycleTest {

    /**
     * 循环_测试
     *
     * 可使用break+标识，跳出多重循环
     */

    /**
     * 场景1：
     */
    @Test
    public void test_compile_error() {

        /**
         * 出现编译错误的写法
         *
         * 结果输出：
         * Integer k 代码处，会抛出语法错误：Declaration not allowed here
         *
         * 结果分析：
         * 1）for中声明的局部变量，只能出现在"{}"中
         *    a）因为声明的局部变量i的作用范围在一个块内，也就是在"{}"中。所以i要么在"{}"中，
         *       要么没有"{}"时的执行语句，没有带有局部变量i
         *
         * 2）尽量使用标准的语句，哪怕循环内只有一行，也最好用花括号括起来，以免产生歧义
         */
//        for (int i = 0; i <= 10; i ++)
//            Integer k = new Integer(i);
//            System.out.println("Hello world");

        /**
         * 编译正确的写法
          */
        for (int i = 0; i <= 10; i ++) {
            Integer k = new Integer(i);
        }
        System.out.println("Hello world");

        /**
         * 编译正确的写法
         */
        for (int i = 0; i <= 10; i ++) //可以不使用"{}"，但仅限于执行语句（也就是不能包含局部变量）
            System.out.println("Hello world 2");
    }

     /**
      * 场景2：循环筛选出100以内的素数
      *
      * 1）素数也叫质数
      * 质数是指在大于1的自然数中，除了1和它本身以外不再有其他因数的自然数。
      *
      * 2）质数p的约数只有两个：1和p。
      *
      * 筛选法的原理：
      * a）所谓的筛选法：是指从小到大筛去已知的一个素数的所有倍数，根据2我们可以筛去“4,6,8,...,100”等数，然后根据3可以筛去“9,15,...,99”等数，
      *    注意此时的6,12早就被作为2的倍数给筛去了，由于4已经被筛去了，所以下一个筛选数是5
      * b）编程原理：定义一个大小为101的数组,把被筛去的数赋值为1,留下未被筛去的并且数组下标大于等于2的数输出，输出的就是质数。
     */
    @Test
    public void test_get_prime_by_cycle() {
        int a[] = new int[101];
        int i, j;
        for (i = 1; i < 101; i++) //此处赋值后，a[0]=0，a[1~101]皆为1
            a[i] = 1;

        for (i = 2; i < 101; i++) {
            if (a[i] != 0) {
                for (j = i + 1; j < 101; ) {
                    if (j % i == 0) { //将下表为i的倍数置为0
                        a[j] = 0;
                    }
                    j = j + 1;
                }
            }
        }

        for (i = 2; i < 101 ; i++)
            if (a[i] != 0) {
                System.out.println(i);
            }
    }

    /**
     * 场景3：使用开根号的方式筛选出100以内的素数
     */
    @Test
    public void test_get_prime_by_sqrt() {
        int a[] = new int[101];
        int i, j, k;
        for (i = 1; i < 100; i++) {
            k = (int)sqrt(i);

            for (j = 2; j <= k; j++) {
                if (i % j == 0)
                    break;
            }
            if (j > k && i > 1) { //1不是素数，需要排除掉
                System.out.println("  " + i);
            }
        }
    }

    /**
     * 场景4：使用break+标识，可跳出多重循环
     */
    @Test
    public void skipCycle() {
        out: //可定义标识
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (j >= 2)
                    break out; //跳到指定标识位置，跳出循环
                System.out.println(j);
            }
        }
        System.out.println("break");

        /**
         * 输出结果：
         * 0
         * 1
         * break
         */
    }
}
