package com.csy.spring.basic;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("pig_v2")
public class Pig {

    @Resource
    private ApplicationContext applicationContext;

    public void getAnimalInfo() {
        Animal animal = applicationContext.getBean(Animal.class);
        System.out.println("从上下文获取到信息：" + animal.getName());
    }
}
