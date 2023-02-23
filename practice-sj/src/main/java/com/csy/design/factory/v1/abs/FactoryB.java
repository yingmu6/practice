package com.csy.design.factory.v1.abs;

/**
 * @author : chensy
 * Date : 2020/11/17 下午7:22
 */
public class FactoryB implements Product {
    @Override
    public Tv produceTv() {
        return new ChangHong();
    }

    @Override
    public Car produceCar() {
        return new DaZong();
    }
}
