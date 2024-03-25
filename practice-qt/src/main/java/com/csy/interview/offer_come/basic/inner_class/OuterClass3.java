package com.csy.interview.offer_come.basic.inner_class;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class OuterClass3 {

    private Logger logger = LoggerFactory.getLogger(OuterClass3.class);

    /**
     * 局部内部类
     */
    private static int a = 1;

    private int b = 2;

    public void partClassTest(int c) { //方法参数可以是final，也可以不是final
        int d = 4;
        final int e = 5; //局部变量可以是final，也可以不是final
        class PartClass { //作用范围只在当前方法内，不能通过方法返回，让其它方法使用
            public void print() {
                logger.info("成员变量：a = {}，b = {}，局部变量：c = {}，d = {}，e = {}", a, b, c, d, e);
            }
        }

        // 要使用对象的方法，需要先创建对象的实例
        PartClass partClass = new PartClass();
        partClass.print();
    }
}
