package com.csy.design.proxy.jdk_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author chensy
 * @date 2023/8/22
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {

        Object result = method.invoke(target, args);
        System.out.println("执行方法：" + method.getName() + "，结果值：" + result);

        return result;
    }
}
