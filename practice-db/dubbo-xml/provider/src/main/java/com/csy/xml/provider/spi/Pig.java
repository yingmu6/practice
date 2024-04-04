package com.csy.xml.provider.spi;

public class Pig implements Animal {
    @Override
    public void cry() {
        System.out.println("猪叫！");
    }
}
