package interview.fourth_book.basic;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * 变量类型
 *
 * @author chensy
 * @date 2023/6/14
 */
public class VarTypeTest { //MsY-Doing

    /**
     * 知识点：进制表示
     *
     * 知识点概括：
     * 1）在整数前加0，表示八进制
     * 2）在整数前加0x，表示十六进制
     */

    /**
     * 场景1：进制表示：
     */
    @Test
    public void test_basic() { //Done
        int i = 012;
        int j = 034;
        int k = (int) 056L; //long类型，转换为int，做强制转换
//        int l = 078; //此处编译错误，因为八进制表示的数范围为 0~7
        System.out.println(i);
        System.out.println(j);
        System.out.println(k);

        /**
         * 结果输出：
         * 10
         * 28
         * 46
         *
         * 结果分析：
         * 1）值计算为：(8^1) * 1 + (8^0) * 2 = 8 + 2 = 10
         * 2）值计算为：(8^1) * 3 + (8^0) * 4 = 24 + 4 = 28
         * 3）值计算为：(8^1) * 5 + (8^0) * 6 = 40 + 6 = 46
         */
    }

    /**
     * 场景2：不同类型变量赋值
     */
    @Test
    public void test_assign() { //Done
        short s = 1;
//        s = s + 1; //此处 s + 1，经过计算后，变为int类型，所以赋值给short类型时，要做强制转换
        s = (short) (s + 1);
        System.out.println(s);

        /**
         * 结果分析：
         * 1）不同类型之间需要转换，低类型向高类型自动转换，高类型向地类型会强制转换
         * 2）8种基本类型的优先级为：
         *    （byte，char，boolean）-> short -> int -> long -> float -> double
         */
    }

    /**
     * 场景3：测试 逻辑运算和按位运算
     *
     * 参考链接：
     * 1）https://www.jianshu.com/p/cfb7df8d3a8b 按位与、或、非、异或
     */
    @Test
    public void test_calculate() { //Done
       int m = 5, n = 5;
       if ((m != 5) && (n++ == 5)) {} // &&短路运算，第一个表达式为假，则整体计算结果为假，则不再进入第二个表达式
       System.out.println("a." + n); // 输出 a.5

       m = n = 5;
       if ((m != 5) & (n++ == 6)) {} // &按位与，两边的表达式都会执行
       System.out.println("b." + n); // 输出 b.6

       m = n = 5;
       if ((m == 5) || (n++ == 5)) {} // ||短路运算，第一个表达为真，则整体计算结果为真，则不再进入第二个表达式
       System.out.println("c." + n); // 输出 c.5

       m = n = 5;
       if ((m == 5) | (n++ == 5)) {} // |按位或，两边的表达式都会执行
       System.out.println("d." + n); // 输出 d.6

       int a = 1, b = 2;
       int c = a & b;
       System.out.println("a&b=" + c); //按位与，两个都为1，位运算的结果才为1，即0&0=0; 0&1=0; 1&0=0; 1&1=1

       /**
        * 输出结果：
        * a.5
        * b.6
        * c.5
        * d.6
        * a&b=0
        *
        * 结果分析：
        * 1）参见上面代码中描述
        */
    }

    /**
     * 场景4：三元表达式中的类型
     */
    @Test
    public void test_ternary_expression() {
        /**
         * Java中的char类型是一种16位的Unicode字符，取值范围为0到65535。 由于char是无符号整数类型，因此不存在负数char值
         */
        char x = 'x'; // 'x' 字符对应的int值为120
        int i = 10;
        System.out.println(false ? i : x);
        System.out.println(false ? 10 : x);
        System.out.println(false ? 65535 : x);
        System.out.println(false ? 65536 : x);

        /**
         * 输出结果：
         * 120
         * x
         * x
         * 120
         *
         * 结果分析：
         * 对于三元表达式：express ？ value1 ： value2
         * 1）若 value1是变量，value2的类型与value1保持一致
         * 2）若 value1是常量，判断value1是否能用value2的类型表示，若能：value2保持原有的类型，若不能，value2的类型要与value1保持一致
         *
         * 1）i是int变量，所以x要变为int的数值
         * 2）10是常量，可以用x对应的char表示，所以x保持原有类型，所以输出x
         * 3）65535是常量，可以用x对应的char表示，所以x保持原有类型，所以输出x
         * 4）65536是常量，但是超出了char的范围了，所以不能用char表示，所以x的值要变为int类型，所以输出120
         */
    }

    /**
     * 场景5：void以及Void类型
     * void：
     * 1）表示什么也不返回
     *
     * Void类：
     * 1）It's not instantiable as its only constructor is private.
     * Therefore, the only value we can assign to a Void variable is null.
     *
     * 2）First, we could use it when doing reflection.
     *
     * 3）Another usage of the Void type is with generic classes
     *
     * https://www.baeldung.com/java-void-type
     */
    @Test
    public void test_void_type_v1() throws NoSuchMethodException { //反射时使用（是赞成使用Void来判断的）
        Class cls = VarTypeTest.class;
        Method method = cls.getMethod("sayHello");
        if (method.getReturnType().isAssignableFrom(Void.TYPE)) { //在反射时，使用Void类型（此处比较的类型，所以为Void.Type，而不是Void.class）
            System.out.println("方法返回值是void类型");
        }
    }

    public void sayHello() {
        System.out.println("hello");
    }

    /**
     * 1）we might want to avoid using Void in generics if possible. Indeed, encountering a return type
     * that represents the absence of a result and can only contain null can be cumbersome.
     * （尽量在使用泛型避免使用Void类型，因为使用Void类型，就要return null代替，返回null是很繁琐的）
     *
     * 2）In order to avoid using a Callable<Void>, we might offer another method taking a Runnable parameter instead
     * （避免使用Callable<Void>，可以使用Runnable来代替）
     */
    @Test
    public void test_void_type_v2() throws Exception { //泛型时使用
        Callable<Void> callable = new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                System.out.println("Hello!");
                return null;
            }
        };

        Assert.assertTrue(VarTypeTest.defer(callable) == null);
    }

    public static <V> V defer(Callable<V> callable) throws Exception {
        return callable.call();
    }

     /**
     * 场景6：Null类型
     * 1）The null reference can be cast to any other reference type（Null类型，可以映射为其它任意类型）
     *
     * 2）null是一个空对象，编译器没有为其分配内存，仅仅表明该引用目前没有指向任何对象
     *
     * https://www.baeldung.com/java-null
     */
    @Test
    public void test_null_type_v1() {
//        boolean a = null; 此处编译错误，null不能映射基本类型
        Boolean b = null;
        Integer c = null;
    }

    @Test
    public void test_null_type_v2() {
        print(1);
        print("hello");

//        print(null); 此处编译错误，Ambiguous（模拟两可的） method call ，因为编译器不清楚选哪个方法调用
    }

    private void print(Integer number) {
        System.out.println(number);
    }

    private void print(String string) {
        System.out.println(string);
    }
}
