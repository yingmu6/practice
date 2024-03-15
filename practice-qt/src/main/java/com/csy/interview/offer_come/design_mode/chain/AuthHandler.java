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
        System.out.println("user auth...");
        if (getHandler() != null) {
            getHandler().operator();
        }
    }

    public static void main(String[] args) {
        AuthHandler authHandler = new AuthHandler("auth");
        BusinessHandler businessHandler = new BusinessHandler("business");
        ResponseHandler responseHandler = new ResponseHandler("response");
        authHandler.setHandler(businessHandler);
        businessHandler.setHandler(responseHandler);
        authHandler.operator();

        /**
         * 输出结果：
         * user auth...
         * business info handler...
         * message response...
         *
         * 结果分析：
         *
         * 问题点答疑：
         */
    }
}
