package com.csy.design.basic;

/**
 * 对象适配器：采取组合被适配对象的方式，进行接口适配
 * @author : chensy
 * Date : 2020-03-01 14:47
 */
public class AdapterForObject implements Target {
    private Adaptee adaptee;
    private String lang;

    public AdapterForObject(Adaptee adaptee, String lang) {
        this.adaptee = adaptee;
        this.lang = lang;
    }

    @Override
    public void sayHello() {
        adaptee.saySepecalHello(lang);
    }
}
