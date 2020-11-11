package relative.design.proxy;

import relative.design.proxy.cglib.CglibProxyFactory;
import relative.design.proxy.cglib.TicketBuy;
import relative.design.proxy.dynamic.ProxyFactory;
import relative.design.proxy.static_p.ITicket;
import relative.design.proxy.static_p.TrainStation;
import relative.design.proxy.static_p.TrainStationAgent;

/**
 * 代理模式测试
 * @author : chensy
 * Date : 2020-03-11 14:31
 */
public class ProxyTest {
    public static void main(String[] args) {
//        staticProxy();
//        dynamicProxy();
        cglibProxy();
    }

    public static void cglibProxy() {
        TicketBuy ticketBuy = new TicketBuy();
        CglibProxyFactory cglibProxyFactory = new CglibProxyFactory(ticketBuy);
        // 直接转换为目标对象，不需要目标对象实现接口
        TicketBuy proxyInstance = (TicketBuy)cglibProxyFactory.getProxyInstance();
        System.out.println(proxyInstance.getTicket(100));

    }

    /**
     * 动态代理：
     * 优点：不用写很多的代理类，运行时动态生成
     * 缺点：要求目标对象必须实现接口
     */
    public static void dynamicProxy() {
        ITicket ticket = new TrainStation();
        ITicket proxy = (ITicket) new ProxyFactory(ticket).getProxyInstance();
        System.out.println(proxy.getTicket(150));
    }

    /**
     * 静态代理：
     * 优点：不改变目标对象，就可以对目标对象进行访问控制，功能增强
     * 缺点：1）需要与目标对象实现相同的接口，接口改变，代理对象也要改变
     *      2）只能代理一种类型的对象，多种类型需要再建另外的代理对象
     */
    public static void staticProxy() {
        // 直接访问目标对象
        ITicket ticket = new TrainStation();
        System.out.println(ticket.getTicket(150));

        // 通过代理对象访问目标对象
        ITicket target = new TrainStation(); //目标对象
        ITicket proxy = new TrainStationAgent(target); //将目标对象委托给代理对象
        System.out.println(proxy.getTicket(150));

//        TrainStation target2 = new TrainStation(); //目标对象
//        ITicket proxy2 = new TrainStationAgent(target2); //将目标对象委托给代理对象
//        System.out.println(proxy2.getTicket(50));
    }
}
