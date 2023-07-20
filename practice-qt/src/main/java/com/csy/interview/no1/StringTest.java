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
     * intern()方法：
     * Returns a canonical representation for the string object. （jdk中的描述）
     * 根据jdk中描述：intern()会返回字符串对象的规范标识，即会从常量池中查找指定字符串，若能找到则返回对应引用，否则把String对象添加到池中，再返回在池中的引用
     *
     * 参考链接：
     * https://www.baeldung.com/string/intern
     */
    @Test
    public void test_intern() {

        // 未使用intern时
        String s1 = new String("aaa777");
        String s2 = "aaa777";
        System.out.println(s1 == s2);

        // 使用intern时
        String s3 = new String("aaa777");
        s3 = s3.intern();
        String s4 = "aaa777";
        System.out.println(s3 == s4);

        /**
         * 输出结果：
         * false
         * true
         *
         * 结果分析：
         * 1）s1是指向堆中对象的引用，s2是指向常量池中字符串的引用，所以两者的内存地址不一样
         * 2）intern()会查找常量池中是否存在"aaa777"的引用，若存在返回，否则把String对象添加到池中，再返回在池中的引用
         */
    }

    /**
     * 场景3：字符串截取程序
     * 题目描述：编写一个字符串截取程序，要求按字节长度截取一个字节数组形式的字符串，字符包含中英文，要求如果最后截取的是半个中文字符，则舍弃它
     */
    @Test
    public void test_intercept() {
        String str = "中国A我";
        byte[] b = "s".getBytes();
        A(str, 4);
    }

    private void A (String str, int i) {
        byte b[] = new byte[1024];
        int num = 0;
        b = str.getBytes();
        if (b[i-1] > 0) {
            System.out.println(new String(b, 0, i));
        } else {
            for (int j = 0; j < i; j++) { // 1）
                if (b[j] < 0) {
                    num ++;
                    num = num % 2;
                } else {
                    num = 0;
                }
            }
            if (num == 0) { // 2）
                System.out.println(new String(b, 0, i));
            } else {
                System.out.println(new String(b, 0, i-1));
            }
        }

        /**
         * 实际输出：
         * 中�
         *
         * 结果分析：
         * 代码1）、2）的逻辑不太清楚，待了解
         */
    }

    /**
     * 场景4：StringBuffer了解
     */
    @Test
    public void test_string_buffer() {
        StringBuffer a = new StringBuffer("A");
        StringBuffer b = new StringBuffer("B");
        operate(a, b);
        System.out.println(a + "," + b);

        /**
         * 预期输出：
         * AB,AB
         *
         * 实际输出：
         * AB,B
         *
         * 结果分析：
         * 画图或者符号表示
         * 1）初始化 a、x => "A"， b、y => "B"
         * 2）执行x.append(y)后，a、x => "AB"，b、y => "B"
         * 3）执行y = x后，y => "AB"，b => "B"
         * 4）operate方法结束后，x、y自然消亡，所以最终，a => "AB"， b => "B"
         */
    }

    private String string; //对象类型的成员变量默认为null
    private int var; // 基本类型的成员变量，系统会设置默认值，int的默认值为0

    /**
     * 场景5：成员变量的默认值
     */
    @Test
    public void test_default() {
        StringTest stringTest = new StringTest();
        System.out.println(stringTest);
    }

    private void operate(StringBuffer x, StringBuffer y) {
        x.append(y);
        y = x; //y指向x，内容为"AB"，但是函数结束后，就会自然消亡
    }
}
