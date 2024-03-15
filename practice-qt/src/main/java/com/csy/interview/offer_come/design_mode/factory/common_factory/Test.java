package com.csy.interview.offer_come.design_mode.factory.common_factory;

/**
 * @author chensy
 * @date 2024/3/14
 */
public class Test {
    public static void main(String[] args) {
        Factory factory = new Factory();
        Phone huawei = factory.createPhone("HuaWei");
        Phone iphone = factory.createPhone("Apple");
        System.out.println(huawei.brand());
        System.out.println(iphone.brand());

        /**
         * 输出结果：
         * this is a huawei phone
         * this is a Apple phone
         *
         * 结果分析：
         *
         * 问题点答疑：
         */
    }
}
