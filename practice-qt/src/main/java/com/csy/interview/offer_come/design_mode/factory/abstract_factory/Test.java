package com.csy.interview.offer_come.design_mode.factory.abstract_factory;

/**
 * @author chensy
 * @date 2024/3/14
 */
public class Test {

    public static void main(String[] args) {
        AbstractFactory phoneFactory = new PhoneFactory();
        Phone phoneHuaWei = phoneFactory.createPhone("HuaWei");
        Phone phoneApple = phoneFactory.createPhone("Apple");
        System.out.println(phoneHuaWei.call());
        System.out.println(phoneApple.call());

        AbstractFactory computerFactory = new ComputerFactory();
        Computer computerHuaWei = computerFactory.createComputer("HuaWei");
        Computer computerApple = computerFactory.createComputer("Apple");
        System.out.println(computerHuaWei.internet());
        System.out.println(computerApple.internet());

        /**
         * 输出结果：
         * call somebody by huawei phone
         * call somebody by apple phone
         * surf the internet by huawei computer
         * surf the internet by apple computer
         *
         * 结果分析：
         *
         * 问题点答疑：
         */
    }
}
