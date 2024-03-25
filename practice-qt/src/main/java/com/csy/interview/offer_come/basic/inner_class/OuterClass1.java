package com.csy.interview.offer_come.basic.inner_class;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class OuterClass1 {

    private static Logger logger = LoggerFactory.getLogger(OuterClass1.class);

     /**
     * 静态内部类
     */
    private static String className = "staticInnerClass";

    private String commonVar = "hello";

    public static class StaticInnerClass {
        public void getClassName() {
            logger.info("直接访问_静态成员变量: {}" , className);
            // logger.info("commonVar: {}" , str); // 此处会编译错误，静态内部类不能直接访问非静态成员变量（静态变量时类加载时就初始化了，而成员变量要对象创建时才初始化，所以访问不了）
            logger.info("间接访问_非静态成员变量：{}", new OuterClass1().getCommonVar());
        }
    }

    public String getCommonVar() {
        return this.commonVar;
    }
}
