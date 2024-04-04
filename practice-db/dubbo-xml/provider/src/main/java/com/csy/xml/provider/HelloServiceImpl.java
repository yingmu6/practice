package com.csy.xml.provider;

import com.basic.use.HelloService;
import org.junit.Test;

/**
 * @author : chensy
 * Date : 2020-03-11 23:58
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String str) {
//        if (str.equals("test")) throw new RpcException("Str 异常");
//        System.out.println("aa:" + str);
        return "你好啊: " + str;
    }

    @Test
    public void test() {
        System.out.println(11);
    }
}
