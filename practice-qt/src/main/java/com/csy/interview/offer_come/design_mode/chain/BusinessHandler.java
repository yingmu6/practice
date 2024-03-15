package com.csy.interview.offer_come.design_mode.chain;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class BusinessHandler extends AbstractHandler implements Handler {

    private String name;

    public BusinessHandler(String name) {
        this.name = name;
    }

    @Override
    public void operator() {
        System.out.println("business info handler...");
        if (getHandler() != null) {
            getHandler().operator();
        }
    }
}
