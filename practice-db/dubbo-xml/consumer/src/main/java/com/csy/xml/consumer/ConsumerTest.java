package com.csy.xml.consumer;

import com.basic.use.HelloService;
import com.basic.use.RequestContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author : chensy
 * Date : 2020-03-12 12:19
 */
public class ConsumerTest {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"consumer.xml"});
        context.start();
        HelloService helloService = (HelloService) context.getBean("helloService");

        // 场景1：获取不到注解（因为helloService.getClass()获取到的是代理实例的Class，而不是RequestContext的Class，所以获取不到）
        if (helloService.getClass().isAnnotationPresent(RequestContext.class)) {
            RequestContext requestContext = helloService.getClass().getAnnotation(RequestContext.class);
            System.out.println("注解值, name=" + requestContext != null ? requestContext.requestName() : "");
        }

        // 场景2：能获取到注解（注解本质上继承Annotation的接口，在编译后，编译器会为注解生成对应的实例）
        if (HelloService.class.isAnnotationPresent(RequestContext.class)) {
            RequestContext requestContext = HelloService.class.getAnnotation(RequestContext.class);
            System.out.println("注解值, name=" + (requestContext != null ? requestContext.requestName() : ""));
        }

        System.out.println(helloService.sayHello("test"));

        System.in.read();
    }
}
