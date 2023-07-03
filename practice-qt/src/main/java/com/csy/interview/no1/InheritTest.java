package com.csy.interview.no1;

import com.csy.interview.no1.inherit_ext.Child;
import org.junit.Test;

/**
 * @author chensy
 * @date 2023/7/3
 */
public class InheritTest {

    /**
     * 继承_执行顺序
     * 1）The order of execution of constructors in Java inheritance is,
     *   firstly, the constructor of the superclass is executed then the constructor of the subclass is executed
     *  （java继承中的构造方法的执行顺序，先执行父类的构造方法，再执行子类的构造方法）
     *
     *  参考链接：
     *  a）https://www.javatpoint.com/order-of-execution-of-constructors-in-java-inheritance
     */

    /**
     * 场景1：测试构造函数的执行顺序
     *      （父类、子类的构造方法的执行顺序）
     */
    @Test
    public void test_constructor_order() {
        new ChildClass();
        new ChildClass(100);

        /**
         * 输出结果：
         * ParentClass without any parameter
         * ChildClass without any parameters
         * ParentClass with a parameter
         * ChildClass with a parameter
         *
         * 结果分析：
         * 在有类继承时，会先执行父类的构造方法，再执行子类的构造方法
         * 1）对于无参的构造方法，系统会默认在构造方法中的第一行调用super()
         * 2）对于有参数的构造方法，需要显示的在构造方法中的第一行调用super(...)父类有参数的构造方法
         */
    }

    /**
     * 场景2：继承中构造方法、代码块、成员变量初始化的执行顺序
     *      （父类、子类的构造方法，以及成员变量初始化的顺序）
     */
    @Test
    public void test_constructor_filed_order() {
        new Z();

        /**
         * 输出结果：
         * 11
         * Y
         * 22
         * 33
         * X
         * Y
         * 44
         * 55
         * Z
         *
         * 结果分析：
         * 1）Z继承了X，所以会先构造父类X，进入父类时，会先运行代码块、成员变量初始化
         *    执行父类的构造方法，最后再构造子类构造方法
         * 2）成员变量的初始化，在构造方法执行前
         */
    }

    /**
     * 场景3：父类、子类中构造方法、静态变量、静态代码块、非静态成员变量、非静态代码块的执行顺序
     * 参考链接：https://www.jianshu.com/p/8155e10e49cd
     */
    @Test
    public void test_all_order() {
        Child child = new Child();

        /**
         * 输出结果：
         * parent：静态代码块：parent静态变量
         * child:静态代码块:child静态变量
         * parent:代码块
         * parent:构造函数parent成员变量
         * child:代码块
         * child:构造函数child成员变量
         *
         * 结果分析：
         * 1）会先执行静态变量和静态代码块
         * 2）然后再执行非静态成员变量、非静态代码块、以及构造方法
         *
         * 总体执行顺序为：
         * 1）parent的静态变量 -> parent的静态代码块 -> child的静态变量 -> child的静态代码块
         * 2）parent的非静态成员变量 -> parent的非静态代码块 -> parent的构造方法
         * 3）child的非静态成员变量 -> child的非静态代码块 -> child的构造方法
         */
    }

    class X {
        {
            System.out.println(11);
        }

        Y b = new Y();

        {
            System.out.println(22);
        }

        X() {
            System.out.println("X");
        }

        {
            System.out.println(33);
        }
    }

    class Y {
        Y() {
            System.out.println("Y");
        }
    }

    class Z extends X {
        Y y = new Y();
        {
            System.out.println(44);
        }

        Z() {
            System.out.println("Z");
        }

        {
            System.out.println(55);
        }
    }

    class ParentClass {
        public ParentClass() {
            System.out.println("ParentClass without any parameter");
        }

        public ParentClass(int i) {
            System.out.println("ParentClass with a parameter");
        }
    }

    class ChildClass extends ParentClass {
        public ChildClass() {
            System.out.println("ChildClass without any parameters");
        }

        public ChildClass(int i) {
            super(i);
            System.out.println("ChildClass with a parameter");
        }
    }
}
