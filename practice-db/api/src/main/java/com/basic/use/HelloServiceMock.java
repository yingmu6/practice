package com.basic.use;

/**
 * @author : chensy
 * Date : 2020/11/13 下午2:42
 */
public class HelloServiceMock implements HelloService{
    @Override
    public String sayHello(String str) { //todo @csy 为啥出现异常，不进入此逻辑
        return "调用异常了";
    }
}
