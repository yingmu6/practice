package com.csy.interview.offer_come.design_mode.interpreter;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class NonterminalExpression implements Expression {

    private Expression left;

    private Expression right;

    public NonterminalExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void interpret(Context ctx) {

    }
}
