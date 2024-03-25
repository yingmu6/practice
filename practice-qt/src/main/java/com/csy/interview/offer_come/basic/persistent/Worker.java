package com.csy.interview.offer_come.basic.persistent;

import java.io.*;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class Worker implements Serializable {

    private static final long serialVersionUID = -5234233744028101156L;

    private String name;

    private transient int salary;

    static int age = 10;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
