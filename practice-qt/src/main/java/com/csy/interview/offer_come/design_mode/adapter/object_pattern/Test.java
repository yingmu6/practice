package com.csy.interview.offer_come.design_mode.adapter.object_pattern;

import com.csy.interview.offer_come.design_mode.adapter.class_pattern.Source;
import com.csy.interview.offer_come.design_mode.adapter.class_pattern.Targetable;

/**
 * @author chensy
 * @date 2024/3/14
 */
public class Test {
    public static void main(String[] args) {
        Source source = new Source();
        Targetable target = new ObjectAdapter(source);
        target.editWordFile();
        target.editTextFile();

        /**
         * 输出结果：
         *
         * 结果分析：
         *
         * 问题点答疑：
         */
    }
}
