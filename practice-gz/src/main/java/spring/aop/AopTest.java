package spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * https://www.jianshu.com/p/994027425b44  Spring(4)——面向切面编程（AOP模块）比较形象
 * @author : chensy
 * Date : 2020-03-11 14:32
 */
public class AopTest {
    public static void main(String[] args) { // todo 为啥输出为空
        ApplicationContext context =
                new ClassPathXmlApplicationContext("aop.xml");
        ServiceImpl service = (ServiceImpl) context.getBean("service", ServiceImpl.class);
        service.say();
    }

    /**
     * https://www.jianshu.com/p/c403609185a5 Spring原理初探--IOC、AOP
     * AOP 面向切面编程，使用的是动态代理模式
     * AOP是什么呢，简单来说，它可以让编程人员在不修改对象代码的情况下，为这个对象添加额外的功能或者限制
     *
     * 使用AOP来灵活处理一些具有横切性质的系统级服务，如事务处理、安全检查、缓存、对象池管理等，
     * 已经成为一种非常适用的解决方案, 它解决了在OOP中大量的重复代码。
     *
     * https://qinzhaokun.github.io/2017/07/12/spring%20AOP%E5%8E%9F%E7%90%86/  Spring AOP原理
     * 相关概念：通知（Advice）、连接点(Join point)、切入点(PointCut)、切面(Aspect)
     *
     */
}

