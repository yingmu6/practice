package com.csy.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * @author chensy
 * @date 13/07/2022
 */
@Service
public class AnimalEventListener implements ApplicationListener<AnimalEventObj> {

    @Override
    public void onApplicationEvent(AnimalEventObj event) {
        System.out.println("收到发送的自定义对象：" + event.getSource().getName());
    }
}
