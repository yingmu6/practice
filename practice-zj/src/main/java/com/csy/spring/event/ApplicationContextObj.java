package com.csy.spring.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author chensy
 * @date 2021/9/9
 */
public class ApplicationContextObj implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        System.out.println("收到事件");
    }


    public static <T> T getBean(String bean, Class<T> t) {
        return applicationContext.getBean(bean, t);
    }
}
