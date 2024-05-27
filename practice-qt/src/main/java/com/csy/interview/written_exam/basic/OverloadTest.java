package com.csy.interview.written_exam.basic;

import org.junit.Test;

/**
 * @author chensy
 * @date 2023/7/5
 */
public class OverloadTest { //@MsY-Done

    /**
     * 知识点：重载
     *
     * 知识点概括：
     * 1）方法重载的特点：
     *    1.1）方法名相同
     *    1.2）参数列表不同
     *     （个数、类型、顺序，只要有一个参数不同，则可以认为是不同的方法）
     *
     * 2）参数列表相同，返回值类型不同不算重载，也不能以方法的访问权限不同来判断，如public、private等
     *
     * 参考链接：
     * a）https://juejin.cn/s/java%E5%88%A4%E6%96%AD%E6%96%B9%E6%B3%95%E7%9A%84%E9%87%8D%E8%BD%BD
     *
     */
    @Test
    public void test_overload() { //Done
        aMethod(1);
        aMethod(1, 3);
        aMethod(12L);

        /**
         * 输出结果：
         * 调用的方法为：aMethod(int i)
         * 调用的方法为：aMethod(int i, int j)
         * 调用的方法为：aMethod(long l)
         *
         * 结果分析：
         * 1）调用重载的方法时，Java编译器会根据方法的参数列表来决定使用哪一个方法。
         */
    }

    public void aMethod(int i) {
        System.out.println("调用的方法为：aMethod(int i)");
    }

    // public int aMethod(int i) {return 0;}; 编译错误：'aMethod(int)' is already defined in 'com.csy.interview.written_exam.basic.OverloadTest'

    public void aMethod(int i, int j) { //方法名相同，参数个数不同，属于重载
        System.out.println("调用的方法为：aMethod(int i, int j)");
    }

    public void aMethod(long l) { //方法名相同，参数类型不同，属于重载
        System.out.println("调用的方法为：aMethod(long l)");
    };

    // protected void aMethod(int i) {} 编译错误：'aMethod(int)' is already defined in 'com.csy.interview.written_exam.basic.OverloadTest'
}
