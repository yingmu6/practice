package com.csy.design.adapter;

import java.util.concurrent.Callable;

/**
 * @author : chensy
 * Date : 2020/10/19 下午11:44
 */
public class Task implements Callable {
    @Override
    public Object call() throws Exception {
        System.out.println("Task");
        return null;
    }
}
