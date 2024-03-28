package com.csy.interview.written_exam.basic;

import com.csy.interview.written_exam.basic.polymor_ext.*;
import org.junit.Test;

/**
 * @author chensy
 * @date 2023/7/6
 */
public class PolymorphismTest {

    /**
     * 多态_测试
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
    public void test_basic() {
        Pa a = new Pb(); //向上转型
        a = new Pa();

        /**
         * 输出结果：
         * A
         * B
         * A
         *
         * 结果分析：
         * 因为Pb继承了Pa，所以会先执行Pa的构造方法，然后再执行Pb的构造方法
         * 所以先输出了 A、B
         * 又由于执行了 new Pa()，所以又输出了A
         *
         * 注意：虽然Pa、Pb都定义的static方法prt()，但是没调用，就不会执行对应的方法
         */
    }

    /**
     * 场景2：父类调用子类重写的方法
     */
    @Test
    public void test_basic_v2() {
        ClassB objectB = new ClassB();
        objectB.printValue(); //子类的引用，调用子类同名的方法
        objectB.printParentValue(); //子类通过super调用父类同名的方法

        ClassA as = (ClassA) objectB;
        as.printValue(); //父类指向子类的实例，调用子类重写的方法

        as.printParentValueV2();
        //as.printSubValueV2(); 此处会报编译错误，找不到该方法（父类不能调用子类自定义的方法，只能调用子类重写的方法）

        as = new ClassA();
        as.printValue();

        /**
         * 输出结果：
         * ClassB
         * ClassA
         * ClassB
         * ClassA V2
         * ClassA
         *
         * 结果分析：
         * ClassB是子类，ClassA是父类
         * 1）父类引用p指向子类的实例时，通过这个引用访问同名方法时，调用的是子类重写的方法
         * 2）子类引用调用同名方法时，默认是重写之后的同名方法
         * 3）子类若想访问父类的同名方法，可以使用super关键字显示说明
         *
         * 注意：形象记忆法 -》 看引用指向哪个实例，就调用哪个实例的方法（非静态方法）
         * 父类引用 = 父类实例  -》 调用父类的方法
         * 父类引用 = 子类实例  -》 调用子类的方法
         * 子类引用 = 子类实例  -》 调用子类的方法
         *
         * （静态方法除外，静态方法是看哪个类调用，就执行哪个类中的方法，还有父类方法再调同名方法时，会调用子类的方法）
         */
    }

    /**
     * 场景3：静态方法调用
     */
    @Test
    public void test_basic_v3() {
        Father father = new Father();
        Father child = new Child();
        System.out.println(father.getName());
        System.out.println(child.getName());

        /**
         * 输出结果：
         * Father
         * Father
         *
         * 结果分析：
         * 因为getName是静态方法，两个静态方法在内存中占用了不同的空间
         * 不存在方法覆盖重写，所以看由哪个类调用，就输出哪个类的方法
         */
    }

    /**
     * 场景4：
     */
    @Test
    public void test_basic_v4() {
        Base base = new MyBase();
        base.add(8); //调用子类的add方法

        System.out.println("-------分隔线-------");
        /**
         * 输出结果：
         * 2
         * 2
         * 6
         * 22
         *
         * 结果分析：
         * 1）new MyBase() 先进入MyBase方法，因为MyBase继承Base，所以先执行Base的构造方法
         * 2）Base是父类，调用add()方法时，会调用子类的构造方法，所以会执行MyBase中的add()方法
         * 3）当父类MyBase构造方法执行完成后，就会执行子类Base的构造方法
         * 4）由于Base base = new MyBase(); 所以父类指向子类实例，会调用子类的add方法
         */

        MyBase myBase = new MyBase();
        myBase.add(4);

        /**
         * 输出结果：
         * 2
         * 2
         * 6
         * 14
         *
         * 结果分析：
         * 1）执行MyBase myBase = new MyBase(); 与上面分析的1）、2）、3）一致
         * 2）MyBase myBase = new MyBase(); 会调用子类的add方法
         */
    }
}
