package com.csy.interview.written_exam.basic.polymor_ext;

/**
 * @author chensy
 * @date 2023/7/6
 */
public class Child extends Father {

    public static String getName() {
        return "Child static getName()";
    }

    public String getAge() {
        return "Child no-static getAge()";
    }
}
