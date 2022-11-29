package com.csy.design.facade;

/**
 * https://www.runoob.com/design-pattern/design-pattern-tutorial.html 设计模式 | 菜鸟教程
 * @author : chensy
 * Date : 2020/10/19 下午10:36
 */
public class FacadeTest {
    public static void main(String[] args) {
        ShapeFacade shapeFacade = new ShapeFacade();
        shapeFacade.drawRectangle();
        shapeFacade.drawRound();
    }
}

/**
 * 外观模式（Facade Pattern）隐藏系统的复杂性，并向客户端提供了一个客户端可以访问系统的接口。这种类型的设计模式属于结构型模式，它向现有的系统添加一个接口，来隐藏系统的复杂性
 * https://www.runoob.com/design-pattern/facade-pattern.html 外观模式
 *
 * 意图：为子系统中的一组接口提供一个一致的界面，外观模式定义了一个高层接口，这个接口使得这一子系统更加容易使用。
 * 优点： 1、减少系统相互依赖。 2、提高灵活性。 3、提高了安全性。
 * 缺点：不符合开闭原则，如果要改东西很麻烦，继承重写都不合适
 */
