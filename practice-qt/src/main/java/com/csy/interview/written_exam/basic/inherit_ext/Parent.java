package com.csy.interview.written_exam.basic.inherit_ext;

/**
 * @author chensy
 * @date 2023/7/3
 */
public class Parent {
    public static String parentStaticString = "parent静态变量"; //成员变量：先声明，后使用
    protected String bb = "parent成员变量";

    static {
        System.out.println("parent：静态代码块：" + parentStaticString);
    }
//    public static String parentStaticString = "parent静态变量"; //若把变量放在 静态块后面，会抛出Illegal forward reference：非法的前向引用

    {
        System.out.println("parent:代码块");
    }

    public Parent() {
        System.out.println("parent:构造函数" + this.bb);
    }
}
