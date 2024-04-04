package com.csy.xml.consumer;

import com.orange.api.IUserInfoApi;
import com.orange.api.common.SaaSRequestContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Method;

/**
 * @author : chensy
 * Date : 2020-03-12 12:19
 */
public class SaasAppTest {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"saas-app.xml"});
        context.start();

        IUserInfoApi userInfoApi = (IUserInfoApi) context.getBean("userInfoApi");

        /**
         * 为什么会获取不到暴露服务实例上的注解？
         * 解答：
         * 1）获取注解是需要获取到Class的对象实例。对于userInfoApi的实例，是通过动态代理产生的对象，如proxy@xxx，该类型上没有声明@SaaSRequestContext注解
         * 2）IUserInfoApi.class拿到的是IUserInfoApi类型的Class对象实例，而该Class对象上是声明了@SaaSRequestContext注解，所以能获取到注解值
         */
        // 场景1：不能获取到暴露对象实例的注解
        if (userInfoApi.getClass().isAnnotationPresent(SaaSRequestContext.class) ) {
            SaaSRequestContext requestContext = userInfoApi.getClass().getAnnotation(SaaSRequestContext.class);
            System.out.println("获取到dubbo服务的注解值：" + requestContext.path() + "，" + requestContext.action());
        }

        // 场景2：能够获取到IUserInfoApi接口上声明的注解（直接通过Class对象，获取二方包暴露接口中的注解值）
        if (IUserInfoApi.class.isAnnotationPresent(SaaSRequestContext.class) ) {
            SaaSRequestContext requestContext = IUserInfoApi.class.getAnnotation(SaaSRequestContext.class);
            System.out.println("获取到的注解值：type = " + requestContext.type());
        }

        // 场景3：能够获取到IUserInfoApi中方法上声明的注解
        for (Method method : IUserInfoApi.class.getMethods()) {
            if (method.isAnnotationPresent(SaaSRequestContext.class)) {
                SaaSRequestContext requestContext = method.getAnnotation(SaaSRequestContext.class);
                System.out.println("获取到注解值：path = " + requestContext.path() + "，action = " + requestContext.action());
            }
        }

        System.out.println("收到的SaaS信息：" + userInfoApi.getUserInfo(1L));
        System.in.read();
    }
}
