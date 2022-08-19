package org.apache.dubbo.demo.impl;

import org.apache.dubbo.demo.api.DemoService;

public class DemoServiceImpl implements DemoService {
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
