package com.csy.spring.basic;

import com.csy.spring.event.ApplicationContextObj;
import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

//        ChannelInboundHandlerAdapter adapter = null;
    }

    public static void basic() {
        Animal animal = new Animal();
        animal.setName("猴子");
        animal.setAge(12);
        System.out.println("普通输出:" + animal.getName() + ";" + animal.getAge());
    }

    public static void basic_spring() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/spring/spring-database-config.xml");
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
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/spring/spring-database-config.xml");
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
