package interview.offer_come.design_mode.visitor;

import org.junit.Test;

/**
 * @author chensy
 * @date 2024/3/27
 */
public class ZdTest { //@MsY-Doing

    /**
     * 知识点：访问者模式
     *
     * 知识点概括：
     *
     * 问题点答疑：
     * 1）观察者模式和访问者模式有关联吗，从语义上理解似乎有些相近？
     *
     */

    @Test
    public void basicUse() { //Done
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
         * 1）CTOVisitor、CEOVisitor都实现了Visitor的访问逻辑，即visit(ProjectElement element)
         *    而站在被访问的内容，即Element角度来看，是接受访问者的访问，即实现了accept(Visitor visitor)
         */
    }
}
