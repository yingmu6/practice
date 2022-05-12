package spring.annotation;

import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

/**
 * @author chensy
 * @date 2022/3/9
 */
@Getter
public class AnotationTest {

    @Resource
    private IAnimal animal;

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");

        for (String beanName : applicationContext.getBeanDefinitionNames()) {
            System.out.println("bean的名称：" + beanName);
        }

    }

    /**
     * todo @csy 注解使用
     * 1）@Bean、@Qualifier、@Configuration
     * 2）@ContextConfiguration、@SpringBootTest()
     * 3）@WebAppConfiguration、@EnableTransactionManagement
     * 4）AbstractTestExecutionListener、TestExecutionListener了解
     */
}


