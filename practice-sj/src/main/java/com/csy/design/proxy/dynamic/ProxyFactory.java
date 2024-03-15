package com.csy.design.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author : chensy
 * Date : 2020/11/11 上午10:41
 */
public class ProxyFactory {
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("代理调用前 xx");
                        Object result = method.invoke(target, args);
                        System.out.println("代理调用后 yy");
                        return result;
                    }
                });
    }

    /**
     * target.getClass().getInterfaces()
     * 1）若class为类，则表示实现的接口列表
     * 2）若class为接口，则表示继承的接口列表
     */

}
