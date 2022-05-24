package spring.aspectj;

/**
 * @author chensy
 * @date 2022/2/15
 */

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class HelloService {

    @Log
    public void sayHi(String msg) {
        System.out.println("\tsayHi:" + msg);
    }

    // 方式一
    public void anotherSayHi(String msg) {
        HelloService helloService = (HelloService) AopContext.currentProxy();
        helloService.sayHi(msg);
    }

    // 方式二
    @Autowired
    ApplicationContext applicationContext;

    public void anotherSayHi2(String msg) {
        applicationContext.getBean(HelloService.class).sayHi(msg);
    }

    @Autowired
    HelloService helloService;

    public void anotherSayHi3(String msg) {
        helloService.sayHi(msg);
    }


    public void sayHelloWithException() {
        Long a = null;
        System.out.println(a.longValue());
    }

    public void testNum() {
        String str = "ssfd";
        try {
            Integer.parseInt(str);
        } catch (Exception e) {
            throw new NumberFormatException("转换错误了");
        }
    }
}
