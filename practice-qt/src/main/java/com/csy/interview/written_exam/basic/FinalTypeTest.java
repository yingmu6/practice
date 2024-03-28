package com.csy.interview.written_exam.basic;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author chensy
 * @date 2023/6/22
 */
public class FinalTypeTest {

    /**
     * final_概述
     * 1）In Java, the final keyword is used to indicate that a variable, method, or class cannot be modified or extended
     *   （final关键字用来修饰变量、方法或类，表示不能修改或扩展）
     *
     * 2）final 变量 -> 将会创建常量
     *   final 方法 -> 阻止方法重写
     *   final 类 -> 阻止类被继承
     *
     * 参考链接：https://www.geeksforgeeks.org/final-keyword-in-java5
     */

    /**
     * 场景1：final 修饰变量
     * 1）修饰变量  This also means that you must initialize a final variable （修饰变量时，必须初始化变量）
     * 2）修饰引用 If the final variable is a reference, this means that the variable cannot be re-bound to reference another object
     *   （final修饰引用时，不能重新绑定另一个对象的引用）
     */
    @Test
    public void test_final_variable() {
        final int a = 3;
        Assert.assertTrue(a == 3);

        // a = 4; 此处会抛出语法错误：Cannot assign a value to final variable 'a' （final修饰变量时，按常理处理，不能变更值）

        final A aClass = new B("zhang");
        Assert.assertTrue(aClass.name.equals("zhang"));

        // aClass = new C("li"); 此处会抛出语法错误：Cannot assign a value to final variable 'aClass'（final修饰引用时，不能变更引用对象）
    }

    class A {
        private String name;

        public void setName(String name) {
           this.name = name;
        }

        final public String getName() {
            return this.name;
        }

        public A () {}

        public A(String name) {
            this.name = name;
        }
    }

    class B extends A {
        public B() {
            super();
        }

        public B(String name) {
            super(name);
        }

        @Override
        public void setName(String name) {
            super.setName("已重写:" + name);
        }

//        @Override
//        public String getName() { //不能重写，因为父类方法，设置为final了
//            return super.getName();
//        }
    }

    class C extends A {
        public C(String name) {
            super(name);
        }
    }

    /**
     * 场景2：final 修饰方法
     */
    @Test
    public void test_final_method() {

        // 父类方法上，没有加final方法，可以重写
        B bClass = new B();
        bClass.setName("zhang");
        Assert.assertTrue(bClass.getName().equals("已重写:zhang"));

        // 父类方法上，加了final方法，不能使用@Override进行标记重写，如B类中的getName方法，会抛出语法错误
    }

    /**
     * 场景3：final 修饰类
     * （若类加上final修饰，则不能通过extends来继承类）
     */

}
