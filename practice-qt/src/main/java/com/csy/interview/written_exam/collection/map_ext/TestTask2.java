package com.csy.interview.written_exam.collection.map_ext;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chensy
 * @date 2023/8/26
 */
public class TestTask2 implements Runnable {

    private ConcurrentHashMap<Integer, Integer> map;

    public TestTask2(ConcurrentHashMap<Integer, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (map) { //对共享对象进行加锁
//                System.out.println("当前线程名：" + Thread.currentThread().getName() + "，map元素值：" + map.get(1));
                map.put(1, map.get(1) + 1);
            }
        }
    }
}
