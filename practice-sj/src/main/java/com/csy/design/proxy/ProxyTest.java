package com.csy.design.proxy;

import com.csy.design.proxy.cglib.CglibProxyFactory;
import com.csy.design.proxy.cglib.TicketBuy;
import com.csy.design.proxy.dynamic.ProxyFactory;
import com.csy.design.proxy.jdk_proxy.HelloProxyImpl;
import com.csy.design.proxy.jdk_proxy.IHelloProxy;
import com.csy.design.proxy.jdk_proxy.MyInvocationHandler;
import com.csy.design.proxy.jdk_proxy.MyInvocationHandler2;
import com.csy.design.proxy.static_p.ITicket;
import com.csy.design.proxy.static_p.TrainStation;
import com.csy.design.proxy.static_p.TrainStationAgent;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
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
     * 问题点：
     * 1）jdk动态代理能否为实现类创建代理，还是只能为接口？
     *    解答：不能为类创建代理，在创建代理类时ProxyClassFactory#apply会检查是否是接口
     *
     * 2）怎样通过工具，如arthas查看jdk产生的代理对象的源码？
     *
     * 参考链接：
     * a）https://www.baeldung.com/java-dynamic-proxies
     */
    @Test
    public void test_jdk_dynamic_proxy_v1() {
        Map mapProxyInstance = (Map) Proxy.newProxyInstance(
                ProxyTest.class.getClassLoader(), new Class[] { Map.class },
                new MyInvocationHandler(new HashMap<>()));

        mapProxyInstance.put("hello", "world");

        mapProxyInstance.get("hello");

        CharSequence csProxyInstance = (CharSequence) Proxy.newProxyInstance(
                ProxyTest.class.getClassLoader(),
                new Class[] { CharSequence.class },
                new MyInvocationHandler("Hello World"));

        csProxyInstance.charAt(0);
        csProxyInstance.length();

        /**
         * 输出结果：
         * Executing put finished in 29729 ns
         * Executing get finished in 11726 ns
         * Executing length finished in 14010
         * Executing charAt finished in 10050 ns
         *
         * 结果分析：
         *
         *
         */
    }

    public static void main(String[] args) throws IOException {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        Map mapProxyInstance = (Map) Proxy.newProxyInstance(
                ProxyTest.class.getClassLoader(), new Class[] { Map.class },
                new MyInvocationHandler(new HashMap<>()));

        mapProxyInstance.put("hello", "world");

        mapProxyInstance.get("hello");

        CharSequence csProxyInstance = (CharSequence) Proxy.newProxyInstance(
                ProxyTest.class.getClassLoader(),
                new Class[] { CharSequence.class },
                new MyInvocationHandler("Hello World"));

        csProxyInstance.charAt(0);
        csProxyInstance.length();
        System.in.read();
    }

    @Test
    public void test_jdk_dynamic_proxy_v2() {
       IHelloProxy helloProxy = (IHelloProxy) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(),
               new Class[]{IHelloProxy.class}, new MyInvocationHandler2());

       helloProxy.setProxyName("hello");

       String proxyName = helloProxy.getProxyName("hello");
       System.out.println("结果值：" + proxyName);

        /**
         * 结果输出：
         *
         * 结果分析：
         *
         * 问题点答疑：
         * 1）为啥MyInvocationHandler2使用method.invoke，会一直调用setProxyName方法，停不下来？
         */
    }

    @Test
    public void test_jdk_dynamic_proxy_v3() { //测试jdk是否能为类创建代理

        try {
            HelloProxyImpl helloProxy = (HelloProxyImpl) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(),
                    new Class[]{HelloProxyImpl.class}, new MyInvocationHandler2());

            helloProxy.setProxyName("hello");

            String proxyName = helloProxy.getProxyName("hello");
            System.out.println("结果值：" + proxyName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /**
         * 输出结果：
         * com.csy.design.proxy.jdk_proxy.HelloProxyImpl is not an interface
         *
         * 结果分析：
         * jdk不能为类创建代理，只能为接口，在代理工厂创建代理类时会做检查ProxyClassFactory#apply是否是接口
         */
    }

}
