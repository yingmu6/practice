package com.basic.use;

import com.alibaba.dubbo.rpc.RpcException;

/**
 * @author : chensy
 * Date : 2020-03-11 23:58
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello(String str) {
        if (str.equals("test")) throw new RpcException("Str 异常");
        System.out.println("aa:" + str);
    }
}
