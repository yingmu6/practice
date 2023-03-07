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

        RequestContext requestContext = helloService.getClass().getAnnotation(RequestContext.class); //todo @csy 此处为什么拿不到接口上注解？
        System.out.println("注解值, name=" + requestContext != null ? requestContext.requestName() : "");

        System.out.println(helloService.sayHello("test"));

        System.in.read();
    }
}
