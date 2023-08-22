package com.csy.design.proxy;

import com.csy.design.proxy.cglib.CglibProxyFactory;
import com.csy.design.proxy.cglib.TicketBuy;
import com.csy.design.proxy.dynamic.ProxyFactory;
import com.csy.design.proxy.jdk_proxy.TimingDynamicInvocationHandler;
import com.csy.design.proxy.static_p.ITicket;
import com.csy.design.proxy.static_p.TrainStation;
import com.csy.design.proxy.static_p.TrainStationAgent;
import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * 代理模式测试
 * @author : chensy
 * Date : 2020-03-11 14:31
 */
public class ProxyTest {

    /**
     * 代理模式_测试
     *
     * 参考链接：
     * a）https://refactoring.guru/design-patterns/proxy
     * b）https://www.runoob.com/design-pattern/proxy-pattern.html 菜鸟教程
     */

    /**
     * 场景1：静态代理：
     * 优点：不改变目标对象，就可以对目标对象进行访问控制，功能增强
     * 缺点：1）需要与目标对象实现相同的接口，接口改变，代理对象也要改变
     *      2）只能代理一种类型的对象，多种类型需要再建另外的代理对象
     */
    @Test
    public void staticProxy() {
        // 直接访问目标对象
        ITicket ticket = new TrainStation();
        System.out.println(ticket.getTicket(150));

        // 通过代理对象访问目标对象
        ITicket target = new TrainStation(); //目标对象
        ITicket proxy = new TrainStationAgent(target); //将目标对象委托给代理对象
        System.out.println(proxy.getTicket(150));
    }

    /**
     * 场景2：动态代理：
     * 优点：不用写很多的代理类，运行时动态生成
     * 缺点：要求目标对象必须实现接口
     */
    @Test
    public void dynamicProxy() {
        ITicket ticket = new TrainStation();
        ITicket proxy = (ITicket) new ProxyFactory(ticket).getProxyInstance();
        System.out.println(proxy.getTicket(150));
    }

    /**
     * 场景3：cglib使用
     */
    @Test
    public void cglibProxy() {
        TicketBuy ticketBuy = new TicketBuy();
        CglibProxyFactory cglibProxyFactory = new CglibProxyFactory(ticketBuy);
        // 直接转换为目标对象，不需要目标对象实现接口
        TicketBuy proxyInstance = (TicketBuy)cglibProxyFactory.getProxyInstance();
        System.out.println(proxyInstance.getTicket(100));
    }

    /**
     * 场景4：JDK代理使用
     *
     * 参考链接：
     * a）https://www.baeldung.com/java-dynamic-proxies
     */
    @Test
    public void test_jdk_dynamic_proxy() {
        Map mapProxyInstance = (Map) Proxy.newProxyInstance(
                ProxyTest.class.getClassLoader(), new Class[] { Map.class },
                new TimingDynamicInvocationHandler(new HashMap<>()));

        mapProxyInstance.put("hello", "world");

        mapProxyInstance.get("hello");

        CharSequence csProxyInstance = (CharSequence) Proxy.newProxyInstance(
                ProxyTest.class.getClassLoader(),
                new Class[] { CharSequence.class },
                new TimingDynamicInvocationHandler("Hello World"));

        csProxyInstance.charAt(0);
        csProxyInstance.length();
    }

    /**
     * 输出结果：
     * Executing put finished in 29729 ns
     * Executing get finished in 11726 ns
     * Executing length finished in 14010
     * Executing charAt finished in 10050 ns
     *
     * 结果分析：
     */
}
