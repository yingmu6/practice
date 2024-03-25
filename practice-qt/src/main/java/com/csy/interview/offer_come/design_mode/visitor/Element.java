package com.csy.interview.offer_come.design_mode.visitor;

/**
 * @author chensy
 * @date 2024/3/16
 */
public interface Element {
    void accept(Visitor visitor);
}