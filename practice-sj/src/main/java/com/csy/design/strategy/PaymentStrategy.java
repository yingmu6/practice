package com.csy.design.strategy;

/**
 * @Author chenSy
 * @Date 2023/04/24 13:34
 * @Description
 */
public interface PaymentStrategy { //支付策略
    void pay(int amount);
}
