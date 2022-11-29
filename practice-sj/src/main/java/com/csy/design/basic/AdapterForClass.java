package com.csy.design.basic;

/**
 * 类适配器：采用继承被适配对象方式，进行接口适配
 * @author : chensy
 * Date : 2020-03-01 14:48
 */
public class AdapterForClass extends Adaptee implements Target {
    private String lang = "zh";

    public AdapterForClass() {
    }

    public AdapterForClass(String lang) {
        this.lang = lang;
    }

    @Override
    public void sayHello() {
        // 进行方法转化以及适配
        saySepecalHello(lang);
    }
}
