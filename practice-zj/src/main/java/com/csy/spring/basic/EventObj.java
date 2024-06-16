package com.csy.spring.basic;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * @author chensy
 * @date 2021/9/8
 */
@Service
public class EventObj implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println("spring.event:::" + applicationEvent.getTimestamp());
    }
}
