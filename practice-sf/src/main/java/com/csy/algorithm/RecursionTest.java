package com.csy.algorithm;

/**
 * 递归测试
 * @author : chensy
 * Date : 2020/7/27 下午5:34
 */
public class RecursionTest {
    public static void main(String[] args) {
//        System.out.println(test(0, 1));
        System.out.println(test1(0, 1));
    }

    /**
     * 抛出栈溢出 StackOverflowError
     * num++，表达式是自增1之前的值，也就是num的值，所以变量值没改变，导致无限调用
     */
    private static int test(int base, int num) {
        if (num > 10) {
            return base;
        }
        base = base + num;
        test(base, num++); //此处循环条件没改变，会StackOverflowError
        return base;
    }

    private static int test1(int base, int num) {
        if (num > 5) {
            return base;
        } else {
            return test1(base + num, num + 1);
        }
        /**
         * num: 1、2、3、4
         * base：1、3、7、15
         */
    }

    /**
     *  stackoverflow是栈溢出，递归可能导致栈溢出的原因是，没有边界检查，导致无限的方法或函数的调用。（栈是有深度限制的）
     *  每个函数或方法的调用都会生成一个栈帧并压入栈中，方法返回后该栈帧被弹出并销毁，
     *  而没有边界的递归调用会无限的生成栈帧并压入栈中，却不会弹出，就导致了栈溢出。而迭代只是在一个方法没执行，
     *  所以只会生成一个栈帧并不会导致栈溢出，不会迭代过程中如果生成大对象，并迭代层级过多可能导致堆的溢出。
     *  https://www.cnblogs.com/illuminator/archive/2013/04/01/2994320.html
     */
}
