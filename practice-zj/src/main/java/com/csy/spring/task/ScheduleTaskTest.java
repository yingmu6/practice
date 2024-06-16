package com.csy.spring.task;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @Author chenSy
 * @Date 2022/12/02 15:27
 * @Description
 */
public class ScheduleTaskTest {
    public static void main(String[] args) throws IOException {
        new ClassPathXmlApplicationContext("META-INF/spring/spring-task.xml");

//        System.in.read(); //只要启动spring容器，就会去执行定时任务，不用此处阻塞主线程去等待读取
    }
}
