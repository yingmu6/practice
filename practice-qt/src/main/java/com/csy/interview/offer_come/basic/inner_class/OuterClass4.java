package com.csy.interview.offer_come.basic.inner_class;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chensy
 * @date 2024/3/16
 */
@Getter
@Setter
public abstract class OuterClass4 { //匿名内部类（抽象类）

    private static Logger logger = LoggerFactory.getLogger(OuterClass4.class);

    private String name;

    public static int age = 12;

    public abstract int workTime();

    public void printInfo() {
        logger.info("人员信息，name：{}，age：{}, workTime：{}", name, age, workTime());
    }
}
