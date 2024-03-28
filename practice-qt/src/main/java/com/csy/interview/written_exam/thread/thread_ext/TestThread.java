package com.csy.interview.written_exam.thread.thread_ext;

import com.csy.interview.written_exam.thread.ThreadRelaTest;

/**
 * @author chensy
 * @date 2023/7/13
 */
public class TestThread extends Thread {
    public TestThread(String n) {
       super(n);
    }

    public void run() {
        synchronized (ThreadRelaTest.class) { // 1）
            for (int i = 0; i < 1000000; i++) {
                int oldV = ThreadRelaTest.TEST; // 2）
                ThreadRelaTest.TEST ++;
                int newV = ThreadRelaTest.TEST;
                if (newV - oldV > 1) {
                    System.out.println("found"); // 3
                }
            }
        }

        System.out.println(this.getName() + " thread end " + ThreadRelaTest.TEST);
    }
}
