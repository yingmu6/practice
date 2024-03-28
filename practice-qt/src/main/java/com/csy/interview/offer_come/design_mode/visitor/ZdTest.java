package com.csy.interview.offer_come.design_mode.visitor;

import org.junit.Test;

/**
 * @author chensy
 * @date 2024/3/27
 */
public class ZdTest {

    /**
     * 知识点：访问者模式
     *
     * 内容概要：
     *
     * 参考链接：
     */

    @Test
    public void basicUse() {
        Element element = new ProjectElement("mobike", "share bicycle");
        element.accept(new CTOVisitor());
        element.accept(new CEOVisitor());

        /**
         * 输出结果：
         * CTO Visitor Element
         * {"projectContent":"share bicycle","projectName":"mobike","visitorName":"CTO","visitorTime":1710547759853}
         * CEO Visitor Element
         * {"projectContent":"share bicycle","projectName":"mobike","visitorName":"CEO","visitorTime":1710547760065}
         *
         * 结果分析：
         * 1）CTOVisitor、CEOVisitor都实现了Visitor的访问逻辑
         *
         * 总结概括：
         *
         */
    }
}
