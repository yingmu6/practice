package com.csy.interview.offer_come.design_mode.visitor;

import com.alibaba.fastjson.JSON;

import java.util.Date;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class CTOVisitor implements Visitor {

    @Override
    public void visit(ProjectElement element) {
        System.out.println("CTO Visitor Element");
        element.signature("CTO", new Date());
        System.out.println(JSON.toJSONString(element));
    }
}
