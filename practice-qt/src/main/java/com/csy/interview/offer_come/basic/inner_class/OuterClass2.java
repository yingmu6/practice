package com.csy.interview.offer_come.basic.inner_class;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class OuterClass2 {

    private Logger logger = LoggerFactory.getLogger(OuterClass2.class);

    /**
     * 成员内部类
     */
    private static int a = 1;

    private int b = 2;

    private MemberInnerClass memberInnerClass = new MemberInnerClass();

    public class MemberInnerClass {
        public void print() {
            logger.info("a = {}", a);
            logger.info("b = {}", b);
        }
    }

    public MemberInnerClass getMemberInnerClass() {
        return memberInnerClass;
    }
}
