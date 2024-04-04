package com.csy.xml.consumer;

import com.orange.api.IUserInfoApi;
import com.orange.client.IUserInfoClient;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author chensy
 * @date 2024/4/3
 */
public class PaasAppTest {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"paas-app.xml"});
        context.start();

        IUserInfoClient userInfoClient = (IUserInfoClient) context.getBean("userInfoClient");

        System.out.println("收到的PaaS信息：" + userInfoClient.getUserInfo(1L));

        System.in.read();
    }
}
