package com.basic.use;

/**
 * @author : chensy
 * Date : 2020-03-11 23:58
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello() {
        System.out.println("客户端请求！");
        return "服务端返回：你好!";
    }
}
