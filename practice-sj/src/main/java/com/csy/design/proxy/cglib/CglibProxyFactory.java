package com.csy.design.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib代理，实现MethodInterceptor接口
 * @author : chensy
 * Date : 2020/11/11 下午12:12
 */
public class CglibProxyFactory implements MethodInterceptor {
    private Object target;

    public CglibProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance(){
        //工具类
        Enhancer en = new Enhancer(); //Enhancer:增强剂
        en.setSuperclass(target.getClass());
        //设置回调
        en.setCallback(this);
        //创建对象的代理
        return en.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib调用前");
        Object result = method.invoke(target, args);
        return result;
    }
}
