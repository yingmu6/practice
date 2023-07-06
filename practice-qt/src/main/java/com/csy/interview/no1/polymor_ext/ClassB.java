package com.csy.interview.no1.polymor_ext;

/**
 * @author chensy
 * @date 2023/7/6
 */
public class ClassB extends ClassA {
    @Override //加不加这个注解都行，会根据方法签名判断是否重写
    public void printValue() {
        System.out.println("ClassB ");
    }

    public void printSubValueV2() {
        System.out.println("ClassB V2");
    }

    public void printParentValue() {
        super.printValue();
    }
}
