package com.csy.interview.no1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author chensy
 * @date 2023/7/7
 */
public class StringTest {

    /**
     * 字符串_测试
     *
     * String常量采用了享元模式（Flyweight）
     * 即会共享字符串，判断字符串是否存在，若存在则使用，否则新创建
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
     * intern()方法：（intern：助理、实习生）
     * 1）intern方法主要把字符串放入字符串常量池中。有两种情况，会将字符串放在常量池中（重点理解）
     *    a）直接使用双引号声明的String对象会直接存储在常量池中，如：String s1 = "aa"
     *    b）通过调用String提供的intern方法把字符串放在常量池中，会检查字符串在常量池中是否存在，存在则不处理，若不存在则放入字符串常量池中
     *
     * 2）Returns a canonical representation for the string object. （jdk中的描述）
     * 根据jdk中描述：intern()会返回字符串对象的规范标识，即会从常量池中查找指定字符串，若能找到则返回对应引用，否则把String对象添加到池中，再返回在池中的引用
     *
     * 注明：
     *    a）字符串放入字符串常量池，需要满足如上1）中的两种情况
     *    b）并不是调用了intern就会将字符串放入字符串常量池中，会先检查字符串是否存在，若存在则不处理。若不存在，要看字符串在堆中是否已存在，已存在只存对象引用，减少对象的创建
     *    c）字符串常量池，在jdk1.7后就从Perm区迁移到堆中，这样对于字符串常量池中可以存对象引用，减少了对象的创建，就大大减少了字符串所占的空间了
     *
     * 参考链接：
     * https://www.baeldung.com/string/intern
     */
    @Test
    public void test_intern_v1() {

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

    @Test
    public void test_intern_v2() {
        String s1 = new String("a");
        s1.intern();
        String s2 = "a";
        System.out.println(s1 == s2);

        String s3 = new String("a") + new String("a");
        s3.intern();
        String s4 = "aa";
        System.out.println(s3 == s4);

        /**
         * 预期结果：
         * true
         * true
         *
         * 实际结果：
         * false
         * true
         *
         * 结果分析：（判断有没有在字符串常量池中，可以看是否用双引号""设置，是否调用了intern）
         * 1）执行s1.intern()时，由于new String("a")已经在字符串常量池中创建了字符串"a"，所以不处理，所以s1与s2指向不同的地址
         * 2）执行s3.intern()时，由于"aa"没有在字符串常量池中，因为jdk1.7以后把字符串常量池移到堆中，也就是堆中的对象，
         *    字符串常量池就不需要再创建一个对象，直接存对象引用即可，所以s3与s4指向相同的地址
         */
    }

    @Test
    public void test_intern_v3() {
        String s1 = new String("a");
        String s2 = "a";
        s1.intern();
        System.out.println(s1 == s2);

        String s3 = new String("a") + new String("a");
        String s4 = "aa";
        s3.intern();
        System.out.println(s3 == s4);

        /**
         * 预期输出：
         * false
         * false
         *
         * 实际输出：
         * false
         * false
         *
         * 结果分析：（画出栈、堆、堆中的字符串常量池进行分析）
         * 1）new String("a"); 会进行两个操作，一是：在字符串常量池创建字符串"a"（此时还没有），二是：在堆中创建字符对象
         * 2）String s2 = "a"; 生成s2引用，指向常量池的字符串"a"对象（字符串常量池中不会创建新的"a"，因为1）中已经创建）
         * 3）new String("a") + new String("a"); 因为String的底层实现为StringBuilder，此处会创建内容为"aa"的字符串对象（此时字符串常量池中还没有）
         * 4）String s4 = "aa"; 在常量池中创建字符串"aa"，并生成s4引用，执行常量池中的字符串"aa"
         * 5）s3.intern();因为此时常量池中已经存在，所以intern方法没有实际的作用，所以s3和s4不是同一个对象
         */
    }

    public static String s = "Hello";
    @Test
    public void test_intern_v4() { //测试字符串常量从jdk1.7以后已经移到堆中（使用jdk1.7以上版本运行）
        try {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                String str = s + s;
                s = str;
                list.add(str.intern()); //依次将字符串加到字符串常量池中
            }
        } catch (OutOfMemoryError error) {
            System.out.println("异常信息：" + error.getMessage());
        }

        /**
         * 输出结果：
         * 异常信息：Java heap space
         *
         * 结果分析：
         * 从抛出的异常信息看，是从堆中抛出的信息，所以说明jdk1.7以后，字符串常量池已经移到堆中了
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

    /**
     * 场景6：String、StringBuffer、StringBuilder、StringTokenizer的区别
     */
    @Test
    public void test_String_StringBuffer_StringBuilder_efficiency() { //测试String、StringBuffer、StringBuilder的效率
        testString();
        testStringBuffer();
        testStringBuilder();

        /**
         * 输出结果：
         * testString runtime:497
         * testStringBuffer runtime:0  （注明：StringBuffer与StringBuilder运行速度比较相近）
         * testStringBuilder runtime:1
         *
         * 结果分析：
         * 在执行方面来看：StringBuilder最高、StringBuffer次之、String最低
         *
         * 结论：
         * 1）如果要操作的数据量比较小，优先使用String类（量多的话，String本质是使用StringBuilder处理的，会产生许多临时对象，触发垃圾回收，影响性能）
         * 2）如果是单线程下处理大量数据，优先使用StringBuilder类（StringBuilder是线程不安全的，单线程下可以使用，减少线程同步的开销）
         * 3）如果是多线程下处理大量数据，优先使用StringBuffer类（因为是线程安全的）
         */
    }

    /**
     * 场景7：测试StringTokenizer（字符串分割类）
     */
    @Test
    public void test_StringTokenizer() {
        StringTokenizer st = new StringTokenizer("Hello World !"); //使用默认的分隔符 " \t\n\r\f"
        while (st.hasMoreTokens()) {
            System.out.println(st.nextToken());
        }

        /**
         * 输出结果：
         * Hello
         * World
         * !
         *
         * 结果分析：
         * 按默认的分隔符分隔
         */

        StringTokenizer st2 = new StringTokenizer("Hello&World&!","&"); //指定自定义分隔符
        while (st2.hasMoreTokens()) {
            System.out.println(st2.nextToken());
        }

        /**
         * 输出结果：
         * Hello
         * World
         * !
         *
         * 结果分析：
         * 可以指定自定义的分隔符
         */
    }

    private void testString() {
        String s = "Hello";
        String s1 = "World";
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            s = s + s1;
        }
        long end = System.currentTimeMillis();
        long runtime = (end - start);
        System.out.println("testString runtime:" + runtime);
    }

    private void testStringBuilder() {
        StringBuilder s = new StringBuilder("Hello");
        String s1 = "World";
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            s.append(s1);
        }
        long end = System.currentTimeMillis();
        long runtime = (end - start);
        System.out.println("testStringBuilder runtime:" + runtime);
    }

    private void testStringBuffer() {
        StringBuffer s = new StringBuffer("Hello");
        String s1 = "World";
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            s.append(s1);
        }
        long end = System.currentTimeMillis();
        long runtime = (end - start);
        System.out.println("testStringBuffer runtime:" + runtime);
    }

    /**
     * 场景8：字符串比较
     * String的equals比较逻辑（查看源码知晓）
     *    a）判断引用是否相等，即this == anObject，若引用相等，则equals返回true
     *    b）若引用不相等，判断输出的参数是否是String类型，即anObject instanceof String
     *       b.1）若是字符串类型：取出String中维护的字符数组char[]，依次比较两个字符串对应字符数组的每一个元素，都相等才相等，否则不相等
     *       b.2）若不是字符串类型，则equals返回false
     */
    @Test
    public void test_compare_with_char_array() {
        String s = "hello";
        String t = "hello";
        char c[] = {'h','e','l','l','o'};
        System.out.println(s.equals(t));
        System.out.println(t.equals(c));
        System.out.println(s == t);
        System.out.println(t.equals(new String("hello")));

        String s1 = "he" + "llo";
        System.out.println(s == s1);

        /**
         * 输出结果：
         * true
         * false
         * true
         * true
         * true
         *
         * 结果分析：
         * 1）s.equals(t)值为true：因为"hello"会存在堆中的字符串常量池中，并且按照享元模式，判断字符串是否存在，不存在才创建，所以s、t是同一个字符串引用
         * 2）t.equals(c)值为false：按照String重写的equals方法，会判断入参是否为String类型，若不是则返回false
         * 3）s == t值为true：==比较的是引用，因为s、t是同一个引用，所以相等
         * 4）t.equals(new String("hello"))值为true：因为String的比较逻辑，会去字符串中的字符数组来比较，字符都相等，则返回true
         * 5）s == s1值为true：因为"he" + "llo"在编译器就会转化"hello"，存在常量区，因为s已经放在常量池中，与s1不会创建新的字符串，s与s1是同一个字符串引用
         */
    }
}
