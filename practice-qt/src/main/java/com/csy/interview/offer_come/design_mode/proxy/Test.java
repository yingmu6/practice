package com.csy.interview.offer_come.design_mode.proxy;

/**
 * @author chensy
 * @date 2024/3/14
 */
public class Test {

    public static void main(String[] args) {
        Company company = new Proxy();
        company.findWorker("Java");
    }
}
