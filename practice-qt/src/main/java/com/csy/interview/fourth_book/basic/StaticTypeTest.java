package com.csy.interview.fourth_book.basic;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @author chensy
 * @date 2023/6/29
 */
public class StaticTypeTest {

    /**
     * static关键字_概述
     * 1）We'll find out how we can apply the static keyword to variables, methods,
     *    blocks, and nested classes, and what difference it makes.
     *   （static关键字可以用于修饰变量、方法、代码块、内部类等）
     *
     * 2）In the Java programming language, the keyword static means that the particular member
     *   belongs to a type itself, rather than to an instance of that type.
     *   （static关键字意味着 指定成员归属于类型本身，而不是属于类型的实例）
     *
     * 3）Since static variables belong to a class, we can access them directly using the class name. So, we don't need any object reference.
     *   （由于static归属于类，所以可以使用类名直接访问，而不需要用对象引用类型）
     *
     * 4）使用static字段的理由：
     *   a）when the value of the variable is independent of objects（将变量值独立于对象）
     *   b）when the value is supposed to be shared across all objects（支持所有对象共享变量）
     *
     * 参考链接：https://www.baeldung.com/java-static
     */

    public static int NUM; //所有的对象共享

    public void increase() {
        NUM ++;
    }

    /**
     * 场景1：static成员变量使用
     */
    @Test
    public void test_static_field() {
        new StaticTypeTest().increase(); //调用方法后，NUM为1
        new StaticTypeTest().increase(); //调用方法后，NUM为2
        Assert.assertTrue(StaticTypeTest.NUM == 2);
    }

    static void increaseNum() {
        NUM ++;
    }

    /**
     * 场景2：static方法
     * We generally use static methods to perform an operation that's not dependent upon instance creation.
     * 1）In order to share code across all instances of that class, we write it in a static method:
     *   （使用static方法，可以让所有实例共享代码）
     *
     * 2）We also commonly use static methods to create utility（有用的） or helper classes so that we can get
     *    them without creating a new object of these classes.
     *   （使用static方法，创建有用的或帮助类）
     */
    @Test
    public void test_static_method() {
        StaticTypeTest.increaseNum();
        StaticTypeTest.increaseNum();
        Assert.assertTrue(StaticTypeTest.NUM == 2);
    }

    public static List<String> ranks = new LinkedList<>();

    static {
        ranks.add("Lieutenant");
        ranks.add("Captain");
        ranks.add("Major");
    }

     /**
     * 场景3：static代码块
     * （使用static代码块，可以做预定义或初始化操作）
     */
    @Test
    public void test_static_block() {
        Assert.assertTrue(ranks.size() == 3);
    }

     /**
     * 场景4：static内部类
     * 1）Java allows us to create a class within a class. It provides a way of grouping（分组） elements that we'll only use in one place.
     *    This helps to keep our code more organized（有组织的） and readable（可阅读的）.
     *
     * 2）In general, the nested class architecture is divided into two types: （内部类分为：静态内部类和非静态内部类）
     *    a）nested classes that we declare static are called static nested classes
     *    b）nested classes that are non-static are called inner classes（非静态内部类：又叫做内部类）
     *
     * 3）The main difference between these two is that the inner classes have access to all members of the enclosing class (including private ones),
     *    whereas the static nested classes only have access to static members of the outer class.
     *   （静态内部类和非静态内部类的区别：非静态内部类可以被外部类的所有成员访问，而静态内部类只能被静态成员访问）
     */
    @Test
    public void test_static_class() {
        Assert.assertTrue(A.name.equals("A"));

        B b = new B();
        Assert.assertTrue(b.name.equals("B"));
    }

    static class A { //静态内部类
        private static String name = "A";
    }

    class B { //内部类，即非静态内部类
        private String name = "B";
    }


    int instanceVariable = 0;

    public static void staticMethod() {
        // System.out.println(instanceVariable); 此处会抛出语法错误：Non-static field 'instanceVariable' cannot be referenced from a static context
    }

    /**
     * 场景5：静态方法中与非静态方法的调用
     * 1）Typically, this error occurs when we use a non-static variable inside a static context.
     *  （通常，当我们在静态上下文中使用非静态变量时，会发生此错误。）
     */
    @Test
    public void test_access_error() {
        // 如staticMethod中的访问，会抛出语法错误

        /**
         * 结果分析：
         * 1）因为static修饰的成员归属于类，而普通的成员变量归属于对象实例，当加载类的时候，
         *    类成员变量或方法就已经被加载，而此处对象还未创建，所以访问实例中的成员就会抛出异常
         */
    }

    /**
     * 场景6：成员方法内部不能定义static变量
     * （因为static是属于类的，而成员方法属于对象的）
     */
    @Test
    public  void test() {
//        static final int i = 0; //此处会抛出语法错误，成员方法中内部不能定义static变量
//        System.out.println(i++);
    }
}
