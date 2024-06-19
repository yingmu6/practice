package relative.basic.inner_class;

import org.junit.Test;

/**
 * 内部类测试
 *
 * @Author chenSy
 * @Date 2023/04/06 00:02
 * @Description
 */
public class TestForInnerClass {

    /**
     * 内部类_整体概述
     * 1）The Java programming language allows you to define a class within another class. Such a class is called a nested class
     * （java编程语言允许你在另一个类中定义类，这个类就叫内部类）
     *
     * 2）Terminology（术语）: Nested classes are divided into（被分为） two categories: non-static and static. Non-static nested classes are called inner classes（内部类）.
     * Nested classes that are declared static are called static nested classes（静态内部类）.
     * （内部类被分为两类：非静态和静态的，非静态的内部类叫做 =》“内部类”，静态的内部类叫做 =》“静态内部类”）
     * class OuterClass {
     *     ...
     *     class InnerClass {
     *         ...
     *     }
     *     static class StaticNestedClass {
     *         ...
     *     }
     * }
     * 注明：There are two special kinds of inner classes: local classes and anonymous classes.（有两种特殊类型的内部类:局部类和匿名类。）
     *
     * 3）A nested class is a member of its enclosing class（封闭类：也叫外部类）. Non-static nested classes (inner classes) have access to other members of the enclosing class,
     * even if they are declared private. Static nested classes do not have access to other members of the enclosing class.
     * As a member of the OuterClass, a nested class can be declared private, public, protected
     * （内部类是封装类的一个成员变量，非静态内部类可以访问封装类的所有成员，甚至是private的成员。静态内部类不能访问封装类的成员，
     *   作为外部类的成员，内部类可以声明为private、public、protected）
     *
     * 4）Why Use Nested Classes?
     *   a）It is a way of logically grouping classes that are only used in one place（这是一种对只在一个地方使用的类进行逻辑分组的方法）
     *   b）It increases encapsulation（内部类增强了封装性）
     *   c）It can lead to more readable and maintainable（可维护的） code（内部类可以提升可阅读性和可维护性）
     *
     * 总结：
     * a）内部类整体分为 非静态内部类 以及 静态内部类
     *    a.1）非静态内部类分为：普通内部类、局部内部类、匿名内部类
     * b）内部类是类中的成员，所有可以访问外部类的任意成员变量和方法
     *    b.1）内部类中只能声明实例成员，不能声明静态成员
     * c）静态内部类，只能访问外部类的静态成员
     *
     * 参考链接：
     * a）https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html oracle官方文档
     * b）https://www.baeldung.com/java-nested-classes
     */
    @Test
    public void basicUse() {
        System.out.println("Inner class:");
        System.out.println("------------");
        OuterClass outerObject = new OuterClass();

        /**
         * 创建内部类（非静态内部类：先创建外部类对象，然后通过 外部类对象.new 创建）
         * a）通过 外部类对象.new 的方式创建
         * b）可通过内部类访问外部的成员变量，甚至是private的变量
         */
        OuterClass.InnerClass innerObject = outerObject.new InnerClass();
        innerObject.accessMembers();

        System.out.println("\nStatic nested class:");
        System.out.println("--------------------");
        OuterClass.StaticNestedClass staticNestedObject = new OuterClass.StaticNestedClass(); //创建静态内部类
        staticNestedObject.accessMembers(outerObject);

        System.out.println("\nTop-level class:");
        System.out.println("--------------------");
        TopLevelClass topLevelObject = new TopLevelClass(); //同级类访问
        topLevelObject.accessMembers(outerObject);
    }

    @Test
    public void test_static_nested() { //测试静态内部类
        /**
         * 写法一：外部类.静态内部类，如InnerClassTest.StaticNested
         */
        TestForInnerClass.StaticNested nested = new TestForInnerClass.StaticNested();
        nested.run();
        static_var++;
        /**
         * 写法二：可以直接new 静态内部类，如new StaticNested()
         */
        StaticNested nested2 = new StaticNested();
        nested2.run();
    }
    private static int static_var = 1;
    private int instance_var = 1;

    public static class StaticNested {

        private void run() {
            // method implementation
            System.out.println("静态内部类：x = " + static_var); //只能访问外部内的静态成员
        }
    }

    /**
     * 局部内部类_概述
     * 1）Local classes are a special type of inner classes – in
     * which the class is defined inside a method or scope block
     * （局部类是一种特殊类型的内部类，其中类定义在方法或作用域块中）
     *
     * 2）Let's see a few points to remember about this type of class:（局部内部类的特性）
     * a）They cannot have access modifiers in their declaration （不能用访问修饰符 修饰局部内部类）
     * b）They have access to both static and non-static members in the enclosing context（能访问封装类的静态和非静态成员变量）
     * c）They can only define instance members（局部内部类中只能定义实例成员变量，也就是成员变量不能使用static修饰）
     *
     */
    @Test
    public void test_local_nested() { //测试局部内部类
        TestForInnerClass innerClassTest = new TestForInnerClass(); //创建外部类
        innerClassTest.run(); //调用外部内的方法（方法中包含局部内部类）
    }

    void run() {
        class Local { //声明局部内部类（局部内部类不能加修饰符，如private、public等）
            private int age = 3;
            //private static int sex = 1; 此处编译报错，不能定义static成员变量
            void run() {
                System.out.println("执行局部内部类：" + static_var + ", i = " + instance_var + ", age = " + age); //能访问静态和非静态成员
            }
        }
        Local local = new Local(); //创建局部内部类
        local.run();
    }


    /**
     * 匿名内部类_概述
     * 1）Anonymous classes can be used to define an implementation of
     * an interface or an abstract class without having to create a reusable（可重用的） implementation.
     * （匿名内部类被用作定义 一个接口或抽象的实现）
     *
     * 2）Let's list a few points to remember about anonymous classes:（匿名内部类的特性）
     * a）They cannot have access modifiers in their declaration
     * b）They have access to both static and non-static members in the enclosing context（能访问封装类的静态和非静态成员变量）
     * c）They can only define instance members（匿名内部类中只能定义实例成员变量，也就是成员变量不能使用static修饰）
     * d）They're the only type of nested classes that cannot define constructors or extend/implement other classes or interfaces
     */
    @Test
    public void test_anonymous_nested() { //测试匿名内部类
        SimpleAbstractClass simpleAbstractClass = new SimpleAbstractClass() {
            private int a = 1;
            // private static int b = 1; 此处编译报错，不能定义static成员变量
            void run() {
                System.out.println("匿名内部类：a = " + a + ", instance_var = " + instance_var + ", static_var = " + static_var);
            }
        };
        simpleAbstractClass.run();
    }

    abstract class SimpleAbstractClass { //匿名内部类
        abstract void run();
    }
}
