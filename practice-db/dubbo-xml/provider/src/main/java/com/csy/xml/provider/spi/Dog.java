package com.csy.xml.provider.spi;

public class Dog implements Animal {
    @Override
    public void cry() {
        System.out.println("狗叫！");
    }
}
