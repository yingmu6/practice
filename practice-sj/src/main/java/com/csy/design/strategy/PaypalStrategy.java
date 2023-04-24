package com.csy.design.strategy;

/**
 * @Author chenSy
 * @Date 2023/04/24 13:37
 * @Description
 */
public class PaypalStrategy implements PaymentStrategy { //贝宝支付（在线支付的策略）
    private String emailId;
    private String password;

    public PaypalStrategy(String email, String pwd){
        this.emailId = email;
        this.password = pwd;
    }

    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid using Paypal.");
    }
}
