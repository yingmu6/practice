package com.csy.interview.offer_come.design_mode.chain;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class ResponseHandler extends AbstractHandler implements Handler {

    private String name;

    public ResponseHandler(String name) {
        this.name = name;
    }

    @Override
    public void operator() {
        System.out.println("message response...");
        if (getHandler() != null) {
            getHandler().operator();
        }
    }
}
