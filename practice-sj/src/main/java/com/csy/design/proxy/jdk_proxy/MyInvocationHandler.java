package com.csy.design.proxy.jdk_proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chensy
 * @date 2023/8/22
 */
public class MyInvocationHandler implements InvocationHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(
            MyInvocationHandler.class);

    private final Map<String, Method> methods = new HashMap<>();

    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;

        for(Method method: target.getClass().getDeclaredMethods()) {
            this.methods.put(method.getName(), method);
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        long start = System.nanoTime();
//        Object result = methods.get(method.getName()).invoke(target, args); // 1)
        Object result = method.invoke(target, args); // 与1）是等价的，即执行目标方法的调用
        long elapsed = System.nanoTime() - start; //运行时间比较短，若用毫秒，都显示0ms，用ns可形象显示

        LOGGER.info("执行方法：{}，结果值：{}，耗费：{} ns", method.getName(), result, elapsed);

        return result;
    }
}
