package com.csy.interview.offer_come.design_mode.chain;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class AuthHandler extends AbstractHandler implements Handler {

    private String name;

    public AuthHandler(String name) {
        this.name = name;
    }

    @Override
    public void operator() {
        System.out.println(name + " auth...");
        if (getHandler() != null) {
            getHandler().operator();
        }
    }
}
