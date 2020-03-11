package relative.spring.aop_copy;

/**
 * @author : chensy
 * Date : 2020-03-11 14:57
 */
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {

    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("aop_copy.xml");
        Landlord landlord = (Landlord) context.getBean("landlord", Landlord.class);
        landlord.service();

    }
}