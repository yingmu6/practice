package com.csy.design.factory.method;

/**
 * @author : chensy
 * Date : 2020/11/17 下午2:38
 */
public interface ProductFactory { //抽象工厂(对工厂进行抽象)
    public Product product();

    /**
     * 工厂方法模式定义一个用于创建对象的接口，让子类决定实例化哪一个类。
     * Factory Method是一个类的实例化延迟到其子类。
     *
     * 在工厂方法模式中，核心的工厂类不再负责所有的产品的创建，
     * 而是将具体创建的工作交给子类去做。这个核心类则摇身一变，成为了一个抽象工厂角色，
     * 仅负责给出具体工厂子类必须实现的接口，而不接触哪一个产品类应当被实例化这种细节
     */
}