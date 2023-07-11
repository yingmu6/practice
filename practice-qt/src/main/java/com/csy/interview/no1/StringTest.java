package com.csy.interview.no1;

import org.junit.Test;

/**
 * @author chensy
 * @date 2023/7/7
 */
public class StringTest {

    /**
     * 字符串_测试
     */

    /**
     * 场景1：字符串中 == 、equals的使用
     * 常量池：它是一个由数组组成的表，用来存储程序中使用的各种常量，包括Class、String、Integer等各种基本的Java数据类型
     */
    @Test
    public void test_compare() {
        String s1 = "abc";
        String s2 = s1;
        String s5 = "abc";
        String s3 = new String("abc");
        String s4 = new String("abc");
        System.out.println("s1 == s5: " + (s1 == s5));
        System.out.println("s1 == s2: " + (s1 == s2));
        System.out.println("s1.equals(s2): " + s1.equals(s2));
        System.out.println("s3 == s4: " + (s3 == s4));
        System.out.println("s1.equals(s4): " + s1.equals(s4));
        System.out.println("s3.equals(s4): " + s3.equals(s4));

        /**
         * 预期输出：
         * true
         * true
         * true
         * false
         * true
         * true
         *
         * 实际输出：
         * s1 == s5: true
         * s1 == s2: true
         * s1.equals(s2): true
         * s3 == s4: false
         * s1.equals(s4): true
         * s3.equals(s4): true
         *
         * 结果分析：
         * 1）"==" 比较基本类型时，判断值是否相等，比较对象类型时，判断是否指向同一内存地址
         * 2）equals是比较两个对象是否一样（即所有成员的值是否相同）
         * 3）字符串，如"abc"是放在常量池中的，在内存中只存在一份副本，所以s1 == s5，同一个字符串，只有一个内存地址
         *   （会在常量池中检查是否存在已有的字符串，存在时，返回原有字符串的内存地址，就不会新建新的内存地址了）
         */
    }

    /**
     * 场景2：String的intern()方法
     *
     *
     * 参考链接：
     * https://www.baeldung.com/string/intern
     */
    @Test
    public void test_intern() {

    }
}
