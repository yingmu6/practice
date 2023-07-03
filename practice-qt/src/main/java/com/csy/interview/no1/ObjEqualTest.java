package com.csy.interview.no1;

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
