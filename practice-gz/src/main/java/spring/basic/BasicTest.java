package spring.basic;

import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.event.AnimalEventListener;
import spring.event.AnimalEventObj;
import spring.event.ApplicationContextObj;

/**
 * @author chensy
 * @date 2021/9/8
 */
@Getter
public class BasicTest {
    public static void main(String[] args) { //todo @csy com.alibaba.spring.util.BeanFactoryUtils使用
//        spring.event.basic();
//        spring();
//        spring.event();
//        basic_spring();

        basic_spring_V2();
        basic_spring_V3();
//        ChannelInboundHandlerAdapter adapter = null;
    }

    public static void basic() {
        Animal animal = new Animal();
        animal.setName("猴子");
        animal.setAge(12);
        System.out.println("普通输出:" + animal.getName() + ";" + animal.getAge());
    }

    public static void basic_spring_V2() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/beanV2.xml");
        Animal animal = (Animal) applicationContext.getBean("animal");
        animal.setName("猴子V2");
        animal.setAge(11);
        System.out.println(animal.getName() + ";;;" + animal.getAge());
    }

    public static void basic_spring_V3() { //特别注意：resources下建立包，不会根据名称建立多级目录的，要逐级new package
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF-V2.spring/beanV3.xml");
        Animal animal = (Animal) applicationContext.getBean("animal");
        animal.setName("猴子V3");
        animal.setAge(11);
        System.out.println(animal.getName() + ";;;" + animal.getAge());
    }

    public static void basic_spring() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        Animal animal = (Animal) applicationContext.getBean("animal");
        animal.setName("猴子");
        animal.setAge(11);
        System.out.println(animal.getName() + ";;;" + animal.getAge());

//        AnimalEventListener animalEventListener = applicationContext.getBean(AnimalEventListener.class);
//        ApplicationEventPublisher eventPublisher = new ApplicationEventPublisher() {
//            @Override
//            public void publishEvent(ApplicationEvent event) {
//
//            }
//
//            @Override
//            public void publishEvent(Object event) {
//
//            }
//        };
//        AnimalEventObj animalEventObj = new AnimalEventObj(animal);
//        animalEventListener.onApplicationEvent(animalEventObj); //todo @csy 感觉没有用到ApplicationEventPublisher，待确定
    }

    public static void spring() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        Animal animal = (Animal) applicationContext.getBean("animal");
        animal.setName("猪");
        animal.setAge(15);

        // spring 默认是单例模式的  https://blog.csdn.net/soonfly/article/details/69359772
        Animal animal2 = (Animal) applicationContext.getBean("animal");
        System.out.println("使用spring:" + animal.getName() + ";" + animal.getAge() + ";" + animal);

        // 当score指定prototype，每次使用对象时，都会创建一个新的bean实例
        System.out.println("使用spring2:" + animal2.getName() + ";" + animal2.getAge() + ";" + animal2);
    }

    public static void event() {
        Animal animal = ApplicationContextObj.getBean("animal", Animal.class);
        animal.setName("test");
        System.out.println(animal.getName());
    }

}
