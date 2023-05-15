package spring.ioc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author : chensy
 * Date : 2020-03-11 14:32
 */
public class IocTest {

    /**
     * IOC_概述
     * 1）IOC：Inversion of Control（控制反转），IoC is also known as（被称为） dependency injection (DI) 也被依赖项注入
     *
     * 2）In short, the BeanFactory provides the configuration framework and basic functionality,
     * and the ApplicationContext adds more enterprise-specific functionality
     * （简而言之，BeanFactory提供了配置框架和基本功能，而ApplicationContext则添加了更多特定于企业的功能）
     *
     * 3）Inversion of Control is a principle in software engineering which transfers the control of
     *    objects or portions of a program to a container or framework.
     * （控制反转是软件工程中的一个原理，它将对对象或程序部分的控制转移到容器或框架中）
     *
     * 4）In contrast with traditional programming, in which our custom code makes calls to a library,
     * IoC enables a framework to take control of the flow of a program and make calls to our custom code
     * （与传统编程(我们的自定义代码对库进行调用)相比，IoC使框架能够控制程序流并对我们的自定义代码进行调用）
     *
     * 5）We can achieve Inversion of Control through various mechanisms such as: Strategy design pattern,
     * Service Locator pattern, Factory pattern, and Dependency Injection (DI).
     * （我们可以通过各种机制实现控制反转，例如:策略设计模式、服务定位器模式、工厂模式和依赖注入(DI)。）
     *
     * 6）Both IoC and DI are simple concepts, but they have deep implications in the way we structure our systems,
     * so they're well worth understanding fully.
     * （IoC和DI都是简单的概念，但它们对我们构建系统的方式有着深刻的影响，因此非常值得充分理解。）
     *
     * 参考链接
     * a）https://docs.spring.io/spring-framework/reference/core/beans/introduction.html 官网描述
     * b）https://www.baeldung.com/inversion-control-and-dependency-injection-in-spring IOC介绍
     */

    /**
     * 场景1：传统方式的依赖实例设置
     * （通过自行创建依赖实例，并设置）
     */
    @Test
    public void test_traditional() {
        Store store = new Store();
        Item item = store.getItem(); //在构造方法中，设置依赖的实例
        System.out.println(item.printItem());

        ItemImpl2 itemImpl = new ItemImpl2();
        itemImpl.setName("武侠书");
        itemImpl.setNum(10);

        store.setItem(itemImpl);
        Item item1 = store.getItem();
        System.out.println(item1.printItem()); //在类的方法参数中，设置依赖的实例
    }

    /**
     * 场景2：依赖注入的各种方式
     * 1）Dependency Injection in Spring can be done through constructors, setters or fields.
     * （Spring中的依赖注入可以通过构造函数、setter或字段来实现）
     */
    @Test
    public void test_ioc_constructor() { //xml中_构造方法_注入依赖
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/spring-ioc-bean.xml");
        StoreV2 storeV20 = (StoreV2) context.getBean("storeV20");
        Item item = storeV20.getItem();
        System.out.println(item.printItem());

        StoreV2 storeV21 = (StoreV2) context.getBean("storeV21");
        System.out.println(storeV21.getDesc());
        System.out.println("storeV21: " + item.printItem());
    }

    @Test
    public void test_ioc_setter() { //xml中_setter方式_注入依赖
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/spring-ioc-bean.xml");
        StoreV2 storeV22 = (StoreV2) context.getBean("storeV22");
        Item item = storeV22.getItem();
        System.out.println("storeV22: " + item.printItem());
    }

    /**
     * 场景3：单实例、多实例使用
     */

    /**
     * 场景4：bean的多种属性类型设置，如List、Map等
     *
     * 参考链接：https://docs.spring.io/spring-framework/reference/core/beans/dependencies/factory-properties-detailed.html
     */

    /**
     * 场景5：延迟加载
     */

    /**
     * 场景6：
     */
}
