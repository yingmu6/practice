package com.csy.interview.offer_come.concurrent.share;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class MyData2 {

    /**
     * 场景：多线程共享数据（将Runnable对象作为一个类的内部类，将共享数据作为这个类的成员变量）
     */
    public static void main(String[] args) {
        final MyData data = new MyData();
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    data.add();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    data.des();
                }
            }).start();
        }
    }

    private int j = 0;
    public synchronized void add() {
        j++;
        System.out.println("线程" + Thread.currentThread().getName() + "j为：" + j);
    }

    public synchronized void dec() {
        j--;
        System.out.println("线程" + Thread.currentThread().getName() + "j为：" + j);
    }

    public int getData() {
        return j;
    }
}
