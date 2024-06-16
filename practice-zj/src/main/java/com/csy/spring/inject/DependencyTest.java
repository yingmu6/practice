package com.csy.spring.inject;

import com.alibaba.fastjson.JSON;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 依赖注入测试
 *
 * @author chensy
 * @date 29/08/2022
 */
public class DependencyTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/spring/spring-database-config.xml");
        AnimalTotal animalTotal = (AnimalTotal) applicationContext.getBean("animalTotal");

        System.out.println(JSON.toJSONString(animalTotal.getAnimalSet())); //todo @csy 此处输出为啥为空
    }
}
