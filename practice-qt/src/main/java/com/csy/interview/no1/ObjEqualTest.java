package com.csy.interview.no1;


import lombok.Getter;
import lombok.Setter;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    /**
     * 场景4：覆盖equals的通用约定（可看Object中equals的方法描述）
     * 1）任何一个类，覆盖Object的非final方法时，都有责任遵守这些通用规定，如果不能做到这一点，其它依赖这些约定的类（如HashMap）就无法结合该类一起正常运行
     *   （违反了这些规定，程序将会表现不正常，甚至崩溃，而且很难找到失败的根源）
     *
     * 2）equals方法实现了等价关系，需要满足的通用约定
     *   a）reflexive 自反性：对于任何非null的引用值x，x.equals(x)必须返回false
     *   b）symmetric 对称性：对于任何非null的引用值x和y，当且仅当x.equals(y)返回true时，y.equals(x)也必须返回true
     *   c）transitive 传递性：对于任何非null的引用值x、y和z，如果x.equals(y)返回true，并且y.equals(z)也返回true，那么x.equals(z)也必须返回true
     *   d）consistent 一致性：对于任何非null的引用值x、y，只要equals的比较操作在对象中所有的信息没有被修改，多次调用x.equals(y)就会一个返回true，或者一致返回false
     *
     * 3）对于任何非null的引用值，x.equals(null)必须返回false
     *
     * 4）覆盖了equals方法的类中，也就必须要覆盖hashCode方法
     *
     * 总结：
     * a）若重写了equals和hashCode方法，要多测试，看是否满足通用约定，写出最佳的方法
     * b）也可以引用验证工具来测试 EqualsVerifier
     *
     * https://www.baeldung.com/java-equals-hashcode-contracts
     */
    @org.junit.Test
    public void test_equals_hashcode_v1() {
        Money1 income = new Money1(10, "RMB");
        Money1 expense = new Money1(10, "RMB");
        System.out.println(income.equals(expense));

        /**
         * 输出结果：
         * false
         *
         * 结果分析：
         * 因为没有重写equals，即该方法来自于Object，是比较对象的引用this == obj的内存地址是否相同，因为new了两个对象，内存地址是不同的
         */
    }

    @Setter
    @Getter
    class Money1 {
        int amount;
        String code;
        public Money1(int amount, String code) {
            this.amount = amount;
            this.code = code;
        }
    }
    @org.junit.Test
    public void test_equals_hashcode_v2() {
        Money2 income = new Money2(10, "RMB");
        Money2 expense = new Money2(10, "RMB");
        System.out.println(income.equals(expense));

        /**
         * 输出结果：
         * true
         *
         * 结果分析：
         * 重写了equals，判断了对象的内容是否相等
         */
    }

    @Setter
    @Getter
    class Money2 {
        int amount;
        String code;
        public Money2(int amount, String code) {
            this.amount = amount;
            this.code = code;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof Money2))
                return false;
            Money2 other = (Money2)o;
            boolean currencyCodeEquals = (this.code == null && other.code == null)
                    || (this.code != null && this.code.equals(other.code));
            return this.amount == other.amount && currencyCodeEquals;
        }
    }

    @Setter
    @Getter
    class Money3 { //通过idea产生的equals、hashCode方法
        final int amount;
        final String code;

        public Money3(int amount, String code) {
            this.amount = amount;
            this.code = code;
        }

        @Override
        public final boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Money3)) {
                return false;
            }
            Money3 money3 = (Money3) o;
            return amount == money3.amount && Objects.equals(code, money3.code);
        }

        @Override
        public final int hashCode() {
            return Objects.hash(amount, code);
        }
    }

    @Setter
    @Getter
    class Money4 {
        final int amount;
        final String code;

        public Money4(int amount, String code) {
            this.amount = amount;
            this.code = code;
        }

        @Override
        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof Money4)) //此处要是写为Money2，就会违反自反性
                return false;
            Money4 other = (Money4)o;
            boolean currencyCodeEquals = (this.code == null && other.code == null)
                    || (this.code != null && this.code.equals(other.code));
            return this.amount == other.amount && currencyCodeEquals;
        }

        public final int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + amount;
            result = prime * result + ((code == null) ? 0 : code.hashCode());
            return result;
        }
    }
    /**
     * 场景5：使用equals的校验工具校验重写的equals方法是否正确
     */
    @org.junit.Test
    public void test_equals_by_tool_v1() { //未重写equals或hashCode方法的场景
        try {
            EqualsVerifier.forClass(Money1.class).verify();
        } catch (AssertionError error) {
            System.out.println("错误信息：" + error.getMessage());
        }

        /**
         * 输出结果：
         * 错误信息：EqualsVerifier found a problem in class Money1.
         * -> Equals is inherited directly from Object.
         * Suppress Warning.INHERITED_DIRECTLY_FROM_OBJECT to skip this check.
         *
         * 结果分析：
         * 从返回的异常信息看出，是不建议直接使用Object的equals的
         */

        try {
            EqualsVerifier.forClass(Money2.class).verify();
        } catch (AssertionError error) {
            System.out.println("错误信息：" + error.getMessage());
        }
        /**
         * 输出结果：
         * 错误信息：EqualsVerifier found a problem in class Money2.
         * -> hashCode: hashCodes should be equal:
         *
         * 结果分析：
         * 从返回的异常信息看出，两个对象相等，需要hashCode也要相等，要重写hashCode方法
         */
    }

    @org.junit.Test
    public void test_equals_by_tool_v2() {
        try {
            EqualsVerifier.forClass(Money3.class).verify();
        } catch (AssertionError error) {
            System.out.println("错误信息：" + error.getMessage());
        }

        /**
         * 输出结果：
         * 校验正常
         *
         * 结果分析：
         * idea产生的equals、hashCode方法，通过EqualsVerifier校验时，需要成员变量和equals、hashCode都要加上final修饰，才能通过校验
         */
    }

    @org.junit.Test
    public void test_equals_by_tool_v3() {
        try {
            EqualsVerifier.forClass(Money4.class).verify();
        } catch (AssertionError error) {
            System.out.println("错误信息：" + error.getMessage());
        }

        /**
         * 输出结果：
         * 校验异常
         *
         * 结果分析：
         * 自定义的equals、hashCode方法，通过EqualsVerifier校验时，需要成员变量和equals、hashCode都要加上final修饰，才能通过校验
         *
         * 遗留问题点：
         * 为啥此处的方法和变量都得为final的？
         */
    }
}
