package com.csy.design.factory.v1.abs;

/**
 * 抽象工厂模式测试
 * @author : chensy
 * Date : 2020/11/17 下午7:10
 */
public class Z_Test {
    public static void main(String[] args) {
        Tv tv = new FactoryA().produceTv();
        Tv tv2 = new FactoryB().produceTv();

        Car car = new FactoryA().produceCar();
        Car car2 = new FactoryB().produceCar();
    }

    /**
     * 抽象通常模式：（产品祖中多个对象设计成一起工作）
     * 抽象工厂模式提供一个创建一系列或相互依赖的对象的接口，而无需指定它们具体的类。
     *
     *抽象工厂模式涉及到的系统角色
     * （1）抽象工厂（AbstractFactory）角色：担任这个角色的是工厂方法模式的核心，它是与应用系统的商业逻辑无关的。通常使用Java 接口或者抽象Java 类实现，而所有的具体工厂类必须实现这个Java 接口或继承这个抽象Java 类。
     * （2）具体工厂类（Conrete Factory）角色：这个角色直接在客户端的调用下创建产品的实例。这个角色含有选择合适的产品对象的逻辑，而这个逻辑是与应用系统的商业逻辑紧密相关的。通常使用具体Java 类实现这个角色。
     * （3）抽象产品（Abstract Product）角色：担任这个角色的类是工厂方法模式所创建的对象的父类，或它们共同拥有的接口。通常使用Java 接口或者抽象Java 类实现这一角色。
     * （4）具体产品（Concrete Product）角色：抽象工厂模式所创建的任何产品对象都是某一个具体产品类的实例。这是客户端最终需要的东西，其内部一定充满了应用系统的商业逻辑。通常使用具体Java 类实现这个角色
     *
     * 优点：
     * （1） 隔离了具体类的生成，使得用户不需要知道什么被创建了。
     * （2） 当一个产品族中的多个对象被设计成一起工作时，它能够保证客户端始终只使用同一个产品族中的对象。
     *
     * 缺点：
     * （1）添加新的产品对像时，难以扩展抽象工厂以便生产新种类的产品

     */
}
