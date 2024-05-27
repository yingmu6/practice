package com.csy.interview.written_exam.basic;

import com.csy.interview.written_exam.basic.polymor_ext.*;
import org.junit.Test;

/**
 * @author chensy
 * @date 2023/7/6
 */
public class PolymorphismTest { //@MsY-Done

    /**
     * 知识点：多态
     *
     * 知识点概要：
     * 1）The dictionary definition of polymorphism（多态） refers to a principle in biology（生物学） in which an organism or species can have many different forms or stages.
     *   This principle can also be applied to object-oriented programming and languages like the Java language.
     *   Subclasses of a class can define their own unique behaviors and yet share some of the same functionality of the parent class.
     *   （多态的定义引用了生物学的一个原理，即生物体或物种可以有许多不同的形式或阶段。这一原则也可以应用于面向对象编程和Java语言等语言。
     *     一个类的子类可以定义它们自己独特的行为，并共享父类的一些相同的功能。）
     *
     * 2）多态是同一个行为具有多个不同表现形式或形态的能力。
     *   多态就是同一个接口，使用不同的实例而执行不同操作
     *
     * 3）多态的优点
     *   1. 消除类型之间的耦合关系
     *   2. 可替换性
     *   3. 可扩充性
     *   4. 接口性
     *   5. 灵活性
     *   6. 简化性
     *
     * 4）多态存在的三个必要条件
     *   1. 继承
     *   2. 重写
     *   3. 父类引用指向子类对象：Parent p = new Child();
     *
     * 参考链接：
     * a）https://docs.oracle.com/javase/tutorial/java/IandI/polymorphism.html 官网描述
     * b）https://www.baeldung.com/java-polymorphism 多态描述
     * c）https://www.runoob.com/java/java-polymorphism.html 多态介绍-菜鸟教程
     * d）https://wiyi.org/java-polymorphism-in-deep.html  Java多态原理-JVM的静态分派和动态分派
     */

    /**
     * 场景1：
     */
    @Test
    public void test_basic() { //Done
        Pa a = new Pb(); //向上转型
        a = new Pa();

        /**
         * 输出结果：
         * Pa static block
         * Pb static block
         * Pa constructor method
         * Pb constructor method
         * Pa constructor method
         *
         * 结果分析：
         * 1）因为Pb继承了Pa，所以会先执行父类Pa的static块，再执行子类Pb的static块
         *    最后再执行父类Pa的构造方法，执行完后再执行子类Pb的构造方法
         *
         * 注意：虽然Pa、Pb都定义的static方法prt()，但是没调用，就不会执行对应的方法，但是static块就执行了
         *      所以方法的调用需要类或对象去调用操作，而代码块不用显示调用，在初始化时就会输出。
         */
    }

    /**
     * 场景2：父类调用子类重写的方法
     */
    @Test
    public void test_basic_v2() { //Done
        ClassB objectB = new ClassB(); //子类的引用指向子类的实例
        objectB.printValue(); //调用子类重写的方法
        objectB.printParentValue(); //调用子类新增的方法
        objectB.printParentValueV2(); //调用子类继承的方法
        System.out.println("----------------");

        ClassA as = objectB; //向上转型（父类引用指向子类的实例）
        as.printValue(); //调用子类重写的方法
        as.printParentValueV2(); //子类未重写父类的方法，则调用从父类继承的方法

        //as.printSubValueV2(); 此处会报编译错误，报"找不到该方法"（父类不能调用子类新增的方法，只能调用子类重写或继承的方法）

        System.out.println("----------------");
        as = new ClassA(); //父类的引用指向父类的实例
        as.printValue();

        /**
         * 输出结果：
         * ClassB（子类） printValue()
         * ClassA（父类） printValue()
         * ClassA（父类） printParentValueV2()
         * ----------------
         * ClassB（子类） printValue()
         * ClassA（父类） printParentValueV2()
         * ----------------
         * ClassA（父类） printValue()
         *
         * 结果分析：
         * ClassB是子类，ClassA是父类
         * 1）父类引用指向子类的实例时，通过这个引用访问方法时，调用的是子类重写或继承的方法
         * 2）子类引用指向子类的实例时，调用的是子类重写或继承或新增的方法
         * 3）子类若想访问父类的同名方法，可以使用super关键字显示说明
         *
         */
    }

    /**
     * 场景3：静态方法调用
     */
    @Test
    public void test_basic_v3() { //Done
        Father father = new Father();
        Father child = new Child();
        Child child1 = new Child();
        System.out.println("------静态方法调用------");
        System.out.println(father.getName());
        System.out.println(child.getName()); //静态方法不会被重写，哪个类的引用调用，就输出哪个类中的静态方法
        System.out.println(father.getNameV2());
        System.out.println(child1.getNameV2()); //子类继承父类的静态方法
        System.out.println("------非静态方法调用------");
        System.out.println(father.getAge());
        System.out.println(child.getAge()); //普通方法的调用

        /**
         * 输出结果：
         * ------静态方法调用------
         * Father static getName()
         * Father static getName()
         * Father static getNameV2()
         * Father static getNameV2()
         * ------非静态方法调用------
         * Father no-static getAge()
         * Child no-static getAge()
         *
         * 结果分析：
         * 1）因为getName是静态方法，两个静态方法在内存中占用了不同的空间，
         *    不存在方法覆盖重写，所以看由哪个类的引用调用，就输出哪个类的方法
         *
         * 2）对于非静态方法，即普通的成员方法，就要看引用最终指向的实例是哪个类，
         *    就调用哪个类中的方法
         *
         * 3）子类虽然不能重写父类的静态方法，但可以继承父类的静态方法
         */
    }

    /**
     * 场景4：
     */
    @Test
    public void test_basic_v4() { //Done
        Base base = new SubBase();
        base.add(8);

        System.out.println("-------分隔线-------");

        SubBase subBase = new SubBase();
        subBase.add(4);

        /**
         * 输出结果：
         * SubBase.add()：i = 2
         * Base()：i = 2
         * SubBase.add()：i = 6
         * SubBase()：i = 6
         * SubBase.add()：i = 22
         * -------分隔线-------
         * SubBase.add()：i = 2
         * Base()：i = 2
         * SubBase.add()：i = 6
         * SubBase()：i = 6
         * SubBase.add()：i = 14
         *
         * 结果分析：
         * 1）创建子类new SubBase()对象时，会先创建父类对象new Base()，父类构造方法中调用
         *    add()方法被子类重写了，所以会调用子类的方法SubBase.add()
         *
         * 2）当父类的成员变量、构造方法完成初始化后，就会调用子类成员变量、构造方法的初始化
         */
    }
}
