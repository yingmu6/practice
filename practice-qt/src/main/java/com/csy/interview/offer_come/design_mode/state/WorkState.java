package com.csy.interview.offer_come.design_mode.state;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class WorkState extends AbstractState {
    @Override
    public void action(Context context) {
        System.out.println("state change to work state ");
        System.out.println("work state action is meeting，design，coding...");
    }
}
