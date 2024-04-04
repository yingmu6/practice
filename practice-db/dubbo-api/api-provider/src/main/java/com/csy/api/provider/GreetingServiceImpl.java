package com.csy.api.provider;

import com.basic.use.Fruit;
import com.basic.use.FruitEnum;
import com.basic.use.GreetingService;

/**
 * @author chensy
 * @date 2021/4/19
 */
public class GreetingServiceImpl implements GreetingService {
    @Override
    public void setMsg(String msg) {
        
    }

    @Override
    public String getMsg() {
        return null;
    }

    @Override
    public String hello() {
        System.out.println("你好 Greeting! api");
        return "hello GreetingServiceImpl API";
    }

    @Override
    public String hello(String msg) {
        return "API " + msg;
    }

    @Override
    public String hello(Integer num) {
        return "API " + num;
    }

    @Override
    public String hello(Fruit fruit) {
        return "API" + fruit.getPrice();
    }

    @Override
    public String hello(FruitEnum fruitEnum) {
        return "API" + fruitEnum.name();
    }
}
