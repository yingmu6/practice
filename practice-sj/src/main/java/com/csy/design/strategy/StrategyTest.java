package com.csy.design.strategy;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author chenSy
 * @Date 2023/02/22 09:59
 * @Description
 */
public class StrategyTest {

    /**
     * 相关描述：
     * 1）Strategy design pattern is one of the behavioral design pattern（行为的设计模式）. Strategy pattern is used when we
     * have multiple algorithm for a specific task and client decides the actual implementation to be used at runtime
     *
     * 2）策略模式作为一种软件设计模式，指对象有某个行为，但是在不同的场景中，该行为有不同的实现算法
     * 策略模式： 定义了一族算法； 封装了每个算法； 这族的算法可互换代替
     *
     * 3）One of the best example of strategy pattern is Collections.sort() method that takes Comparator parameter.
     * Based on the different implementations of Comparator interfaces（Collections.sor()方法就很好用到的策略模式）
     *
     * 用例参考：https://www.digitalocean.com/community/tutorials/strategy-design-pattern-in-java-example-tutorial
     */

    /**
     * 场景1：使用方法传参指定策略
     */
    @Test
    public void useStrategyByMethodParams() {
        ShoppingCart cart = new ShoppingCart();

        Item item1 = new Item("1234",10);
        Item item2 = new Item("5678",40);

        cart.addItem(item1);
        cart.addItem(item2);

        //---通过参数的形式，指定不同的策略--------
        //pay by paypal
        cart.pay(new PaypalStrategy("myemail@example.com", "mypwd"));

        //pay by credit card
        cart.pay(new CreditCardStrategy("Pankaj Kumar", "1234567890123456", "786", "12/15"));
    }

    public static Map<String, PaymentStrategy> STRATEGY_MAP = new HashMap<>();

    @BeforeClass
    public static void init() {
        STRATEGY_MAP.put("payPal", new PaypalStrategy("myemail@example.com", "mypwd"));
        STRATEGY_MAP.put("creditCard", new CreditCardStrategy("Pankaj Kumar", "1234567890123456", "786", "12/15"));
    }

    /**
     * 场景2：将策略的标识与具体策略实例缓存映射起来，可在运行时根据参数选择策略实例
     * 1）在启动时，先把策略的标志与策略模式实例或bean名称等先缓存起来
     * 2）在运行时，就可以根据具体的参数标志，选择具体的实例
     * （该种方法，在实际工作场景中用的比较多，一般都是在spring容器启动好后，把策略标志与策略实例的bean缓存起来，
     * 切换策略，不需要改动代码，类似实现多个if或switch的功能）
     */
    @Test
    public void useStrategyByFlag() {
        ShoppingCart cart = new ShoppingCart();

        Item item1 = new Item("123477",20);
        Item item2 = new Item("567877",50);

        cart.addItem(item1);
        cart.addItem(item2);

        cart.pay(STRATEGY_MAP.get("payPal"));
        cart.pay(STRATEGY_MAP.get("creditCard"));
    }

}
