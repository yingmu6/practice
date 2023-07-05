package com.csy.interview.no1;

import org.junit.Test;

/**
 * @author chensy
 * @date 2023/7/5
 */
public class OverloadTest {

    /**
     * 重载_测试
     *
     * 方法重载的特点：
     * 1）方法名相同
     * 2）参数列表不同
     *  （个数、类型、顺序，只要有一个参数不同，则可以认为是不同的方法）
     *
     * 但是参数列表相同，返回值类型不同不算重载，也不能以方法的访问权限不同来判断，如public、private等
     *
     * 参考链接：
     * a）https://juejin.cn/s/java%E5%88%A4%E6%96%AD%E6%96%B9%E6%B3%95%E7%9A%84%E9%87%8D%E8%BD%BD
     *
     */
    @Test
    public void test_overload() {
        aMethod(1);
        aMethod(1, 3);
        aMethod(12L);
    }

    public void aMethod(int i) {}

    // public int aMethod(int i) {return 0;}; 编译错误：'aMethod(int)' is already defined in 'com.csy.interview.no1.OverloadTest'

    public void aMethod(int i, int j) {} //方法名相同，参数个数不同，属于重载

    public void aMethod(long l) {}; //方法名相同，参数类型不同，属于重载

    // protected void aMethod(int i) {} 编译错误：'aMethod(int)' is already defined in 'com.csy.interview.no1.OverloadTest'
}
