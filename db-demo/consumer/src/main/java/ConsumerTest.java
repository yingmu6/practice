import com.basic.use.HelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author : chensy
 * Date : 2020-03-12 12:19
 */
public class ConsumerTest {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"consumer.xml"});
        context.start();

        HelloService helloService = (HelloService) context.getBean("helloService");
        System.out.println(helloService.sayHello("test"));

        System.in.read();
    }
}
