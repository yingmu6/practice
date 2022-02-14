package com.basic.use;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author : chensy
 * Date : 2020-03-12 12:21
 */
public class ProviderStartUp {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"provider.xml"});
        context.start();

        System.in.read(); // 按任意键退出
    }
}
