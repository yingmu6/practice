package com.csy.interview.offer_come.design_mode.adapter.interface_pattern;

/**
 * @author chensy
 * @date 2024/3/14
 */
public class SourceSub1 extends AbstractAdapter {

    @Override
    public void editTextFile() {
        System.out.println("a text file editing");
    }
}
