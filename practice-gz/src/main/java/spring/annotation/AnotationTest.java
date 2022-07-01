package spring.annotation;

import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author chensy
 * @date 2022/3/9
 */
@Getter
public class AnotationTest {

//    public IAnimal getAnimal() {
//        return animal;
//    }
//
//    public void setAnimal(IAnimal animal) {
//        this.animal = animal;
//    }

//    @Resource
//    private IAnimal animal;

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");

//        for (String beanName : applicationContext.getBeanDefinitionNames()) {
//            System.out.println("bean的名称：" + beanName);
//        }

        IAnimal animal = applicationContext.getBean(IAnimal.class);
        animal.cry(); //todo @csy 此处为何报错


    }

    /**
     * todo @csy 注解使用
     * 1）@Bean、@Qualifier、@Configuration
     * 2）@ContextConfiguration、@SpringBootTest()
     * 3）@WebAppConfiguration、@EnableTransactionManagement
     * 4）AbstractTestExecutionListener、TestExecutionListener了解
     */
}


