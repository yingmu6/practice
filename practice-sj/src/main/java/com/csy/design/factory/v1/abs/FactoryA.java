package com.csy.design.factory.v1.abs;

/**
 * @author : chensy
 * Date : 2020/11/17 下午7:17
 */
public class FactoryA implements Product {
    @Override
    public Tv produceTv() {
        return new Sony();
    }

    @Override
    public Car produceCar() {
        return new Audi();
    }
}
