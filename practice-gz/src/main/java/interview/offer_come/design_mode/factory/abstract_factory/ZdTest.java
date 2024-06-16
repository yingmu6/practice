package interview.offer_come.design_mode.factory.abstract_factory;

import org.junit.Test;

/**
 * @author chensy
 * @date 2024/3/14
 */
public class ZdTest {

    /**
     * 知识点：抽象工厂模式
     *
     * 知识点概括：
     *
     * 问题点答疑：
     *
     */
    @Test
    public void basicUse() {
        AbstractFactory phoneFactory = new PhoneFactory(); //手机工厂
        Phone phoneHuaWei = phoneFactory.createPhone("HuaWei");
        Phone phoneApple = phoneFactory.createPhone("Apple");
        System.out.println(phoneHuaWei.call());
        System.out.println(phoneApple.call());

        AbstractFactory computerFactory = new ComputerFactory(); //计算机工厂
        Computer computerHuaWei = computerFactory.createComputer("HuaWei");
        Computer computerApple = computerFactory.createComputer("Apple");
        System.out.println(computerHuaWei.internet());
        System.out.println(computerApple.internet());

        /**
         * 输出结果：
         * call somebody by huawei phone
         * call somebody by apple phone
         * surf the internet by huawei computer
         * surf the internet by apple computer
         *
         * 结果分析：
         * 1）获取抽象工厂AbstractFactory的具体工厂实例PhoneFactory，因为该工厂生产手机，所以就实现了createPhone的逻辑，再根据不同的品牌执行对应的逻辑
         * 2）获取抽象工厂AbstractFactory的具体工厂实例ComputerFactory，因为该工厂生产计算机，所以就实现了createComputer的逻辑，再根据不同的品牌执行对应的逻辑
         *
         * 结果概括：
         * 1）抽象工厂就是对具体工厂的抽象，里面包含了多个工厂的抽象的逻辑，具体的工厂只需实现所需的逻辑，其它逻辑不用实现
         * 2）相比普通的具体工厂而言，具体工厂只能产生同一个类型的对象实例，而抽象工厂可以产生不同类型的实例
         */
    }
}
