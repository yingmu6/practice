package com.csy.interview.offer_come.design_mode.interpreter;

/**
 * @author chensy
 * @date 2024/3/16
 */
public interface Expression {
    void interpret(Context ctx);
}