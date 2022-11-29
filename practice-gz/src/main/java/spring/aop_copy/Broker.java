package spring.aop_copy;

/**
 * @author : chensy
 * Date : 2020-03-11 14:52
 */

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect  // 切面
class Broker { //中介（代理类）

    @Before("execution(* relative.spring.aop_copy.Landlord.service())")
    public void before() { //在目标方法之前执行
        System.out.println("带租客看房");
        System.out.println("谈价格");
    }

    @After("execution(* relative.spring.aop_copy.Landlord.service())")
    public void after() { //在目标方法之后执行
        System.out.println("交钥匙");
    }
}