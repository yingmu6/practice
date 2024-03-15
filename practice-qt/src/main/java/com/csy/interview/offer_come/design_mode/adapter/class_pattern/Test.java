package com.csy.interview.offer_come.design_mode.adapter.class_pattern;
/**
 *
 * @author chensy
 * @date 2024/3/14
 */
public class Test {
    /**
     * 类的适配器
     */
    public static void main(String[] args) {
       Targetable target = new Adapter();
       target.editTextFile();
       target.editWordFile();

        /**
         * 输出结果：
         * a text file editing
         * a word file editing
         *
         * 结果分析：
         *
         * 问题点答疑：
         */
    }
}
