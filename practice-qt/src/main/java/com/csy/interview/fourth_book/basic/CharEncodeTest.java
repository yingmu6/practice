package com.csy.interview.fourth_book.basic;

import org.junit.Test;

/**
 * 字符编码测试
 * @author chensy
 * @date 2023/6/7
 */
public class CharEncodeTest {

    @Test
    public void test_code() {

        /**
         * JVM中字符概述：
         * 1）在Java中字符只以一种形式存在，那就是Unicode（不选择任何具体的编码，直接使用它们在字符集中的编码，这是唯一的方法）
         * 2）在内存中，是可以有多种字符存在的，如GB2312、UTF-16、BIG5等编码形式
         * 3）JVM的约定使得一个字符分为两部分：JVM内部和OS的文件系统。在JVM内部，统一使用Unicode表示，当这个字符被从JVM内部转移到外部（即文件系统中的一个文件的内容时）
         *    就会进行编码转换，就会使用到具体的编码方案。所有编码转换只发生在边界的地方，JVM和OS的交界处，也就是各种输入/输出流起作用的地方。
         * 4）Unicode 当然是一个很大的集合，现在的规模可以容纳100多万个符号。Unicode只是一个符号集，它只规定了符号的二进制代码，却没有规定这个二进制代码应该如何存储。
         *
         * 总结：各种编码的不同，都是为了都是为了节省你的硬盘和流量
         *
         * 参考链接：
         * a）https://www.baeldung.com/java-char-encoding 字符编码指导
         * b）https://www.ruanyifeng.com/blog/2007/10/ascii_unicode_and_utf-8.html  ASCII、Unicode和UTF-8
         */

        // 字符输出对应的Unicode编码
        char c = '永';
        System.out.format("%x", (short) c); //格式化输出值 6c38

        // 使用Unicode指定一个字符
        char c1 = 0x6c38;
        System.out.println(c1); //输出 "永"
    }
}
