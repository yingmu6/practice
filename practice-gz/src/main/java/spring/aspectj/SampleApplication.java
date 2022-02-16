package spring.aspectj;

/**
 * @author chensy
 * @date 2022/2/15
 */

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan("spring.aspectj")
@Configuration
@EnableAspectJAutoProxy(exposeProxy = true)
public class SampleApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SampleApplication.class);
        HelloService helloService = context.getBean(HelloService.class);
        helloService.sayHi("hi-1");
        System.out.println("\n");
        helloService.anotherSayHi("hi-2");

        System.out.println("\n");
        helloService.anotherSayHi2("hhh");

        System.out.println("\n");
        helloService.anotherSayHi3("hhssh");
    }
}
