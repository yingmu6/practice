package com.csy.design.proxy.jdk_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author chensy
 * @date 2023/8/23
 */
public class MyInvocationHandler2 implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("目标方法调用前：" + method.getName());
//        Object obj = method.invoke(proxy, args);
        System.out.println("目标方法调用后：" + method.getName());
//        return obj;
        return null;
    }
}
