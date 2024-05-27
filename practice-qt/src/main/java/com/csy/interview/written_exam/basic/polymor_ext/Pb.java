package com.csy.interview.written_exam.basic.polymor_ext;

/**
 * @author chensy
 * @date 2023/7/6
 */
public class Pb extends Pa {

    static {
        System.out.println("Pb static block");
    }

    public static void prt() {
        System.out.println("2");
    }

    public Pb() {
        System.out.println("Pb constructor method");
    }
}
