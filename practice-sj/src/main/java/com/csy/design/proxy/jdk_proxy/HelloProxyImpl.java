package com.csy.design.proxy.jdk_proxy;

/**
 * @author chensy
 * @date 2023/8/23
 */
public class HelloProxyImpl implements IHelloProxy {

    private String proxyName;

    private static final String PREFIX = "proxy_";

    @Override
    public String getProxyName(String name) {
        return proxyName;
    }

    @Override
    public void setProxyName(String name) {
        proxyName = PREFIX + name;
    }
}
