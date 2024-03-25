package com.csy.interview.offer_come.design_mode.visitor;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author chensy
 * @date 2024/3/16
 */
@Getter
@Setter
public class ProjectElement implements Element {

    private String projectName;

    private String projectContent;

    private String visitorName;

    private Date visitorTime;

    public ProjectElement(String projectName, String projectContent) {
        this.projectName = projectName;
        this.projectContent = projectContent;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void signature(String visitorName, Date visitorTime) {
        this.visitorName = visitorName;
        this.visitorTime = visitorTime;
    }

    public static void main(String[] args) {
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
         *
         * 问题点答疑：
         */
    }
}
