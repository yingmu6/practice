package com.csy.interview.offer_come.design_mode.template;

import org.junit.Test;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class ZdTest {

    /**
     * 知识点：模版模式
     *
     * 总结概括：
     *
     * 参考链接：
     *
     */

    /**
     * 场景1：基本使用
     */
    @Test
    public void basicUse() {
        AbstractTemplate template1 = new TakeMoney();
        template1.templateMethod();

        AbstractTemplate template2 = new SaveMoney();
        template2.templateMethod();

        /**
         * 输出结果：
         * checkNumber......
         * queue up......
         * take money form bank.
         * business finished, service evaluation......
         * checkNumber......
         * queue up......
         * save money to the bank.
         * business finished, service evaluation......
         *
         * 结果分析：
         *
         * 问题点答疑：
         */
    }
}
