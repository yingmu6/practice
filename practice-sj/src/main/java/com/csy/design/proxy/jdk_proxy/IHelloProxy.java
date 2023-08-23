package com.csy.design.proxy.jdk_proxy;

/**
 * @author chensy
 * @date 2023/8/23
 */
public interface IHelloProxy {

    String getProxyName(String name);

    void setProxyName(String name);
}