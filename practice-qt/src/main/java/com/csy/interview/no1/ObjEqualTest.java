package com.csy.interview.no1;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chensy
 * @date 2023/7/3
 */
public class ObjEqualTest {

    /**
     * 场景1：对象_比较
     */
    @org.junit.Test
    public void test_obj_equals() {
        List list = new ArrayList<>();
        Test test1 = new Test("object");
        Test test2 = new Test("object");
        Test test3 = new Test("object");
        Object test4 = new Test("object");
        list.add(test1);

        System.out.println(list.contains(test2)); //通过列表进行比较，没有调用Test的equal
        System.out.println(test2.equals(test3));
        System.out.println(test3.equals(test4));
        System.out.println(test4.equals(test3));

        /**
         * 输出结果：
         * false
         * true
         * false
         * false
         *
         * 结果分析：
         * 1）因为list中的元素为test1，不是test2，所以返回false。（contains方法有调用Object的equals()方法）
         * 2）test2与test3都是Test类型，所以会调用Test的equals方法，该方法中是根据value是否相等，来判断对象是否相等，因为value都相等，所以返回true
         * 3）和4）因为test4是Object类型，所以会调用Object的equals方法，因为该方法默认是比较对象的内存地址，两个对象内存地址不同，所以返回false
         */
    }

    /**
     * 场景2：嵌套内部类
     */
    static class one { //内部类可以多层级嵌套，没有层级限制
        private static class two { //内部类可以是私有的
            public static void main(String[] aaa) {
                System.out.println("two");
            }
        }
    }

    /**
     * 场景3：封装类型比较
     */
    @org.junit.Test
    public void test_wrapper_class_compare() {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        Integer i = new Integer(1);
        Integer j = new Integer(1);
        System.out.println(c == d);
        System.out.println(e == f);
        System.out.println(c == (a +b));
        System.out.println(g.equals(a + b));
        System.out.println(i == j);

        /**
         * 输出结果：
         * true
         * false
         * true
         * false
         * false
         *
         * 结果分析：
         * 1）因为Integer的内部类IntegerCache缓存池，会缓存-128~127的数值，也就是在这个区别的值是同一个Integer对象
         * 2）由于e、f超过了-128~127区间，所以会为e、f创建不同的对象，所以==比较为false
         * 3）由于a+b=3，c也为3，在缓存区间，所以是同一个对象
         * 4）查看Long的equals，会先判断类型是否为Long，因为a+b是Integer，所以直接返回false
         * 5）i、j通过new，产生了不同的对象，所以==比较的对象地址时不一样的
         */
    }

    class Test {
        private String value = null;

        public Test(String v) {
            value = v;
        }

        public boolean equals(Test o) {
            if (o == this) {
                return true;
            }

            if (o instanceof Test) {
                Test test = (Test) o;
                return value.equals(test.value);
            }
            return false;
        }
    }
}
