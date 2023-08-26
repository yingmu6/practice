package com.csy.design.proxy.jdk_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chensy
 * @date 2023/8/23
 */
public class MyInvocationHandler2 implements InvocationHandler {

    private final Map<String, Method> methods = new HashMap<>();

    IHelloProxy target; //持有目标对象

    public MyInvocationHandler2(IHelloProxy target) {
        this.target = target;

        for (Method method : target.getClass().getDeclaredMethods()) {
            methods.put(method.getName(), method);
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = methods.get(method.getName()).invoke(target, args); //调用目标对象的对应方法，此处的proxy是代理对象

//        Object result = method.invoke(target, args); //也可以不维护methods，直接调用目标对象target的方法
        System.out.println("调用目标对象的方法后：" + method.getName() + "，结果值：" + result);
        return result;
    }
}
