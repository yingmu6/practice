package com.csy.interview.offer_come.design_mode.factory.abstract_factory;

/**
 * @author chensy
 * @date 2024/3/14
 */
public class ComputerFactory extends AbstractFactory{
    @Override
    public Phone createPhone(String brand) {
        return null;
    }

    @Override
    public Computer createComputer(String brand) {
        if ("HuaWei".equals(brand)) {
            return new ComputerHuaWei();
        } else if ("Apple".equals(brand)) {
            return new ComputerApple();
        } else {
            return null;
        }
    }
}
