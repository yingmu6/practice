package com.csy.design.proxy.jdk_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author chensy
 * @date 2023/8/23
 */
public class MyInvocationHandler2 implements InvocationHandler {

    Object target; //持有目标对象

    public MyInvocationHandler2(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result = method.invoke(target, args);
        System.out.println("调用目标对象的方法后：" + method.getName() + "，结果值：" + result);
        return result;
    }
}
