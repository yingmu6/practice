package com.csy.spring.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;

/**
 * @author chensy
 * @date 2022/5/23
 */
@Component
public class AnimalAspect {

    @Around("execution (* spring.inter..*.*(..))")
//    @Around("execution (* spring.aspectj..*.*(..))")
    public void aroundTest(JoinPoint point) {
        try {
            System.out.println("AnimalAspect拦截");
            //执行方法
            ((ProceedingJoinPoint) point).proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {

        }
    }
}
