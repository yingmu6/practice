package com.csy.interview.offer_come.concurrent.share;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class MyData {

    /**
     * 场景：多线程共享数据（将数据抽象成一个类，并将对这个数据的操作封装在类的方法中）
     */
    public void main(String[] args) {
       MyData data = new MyData();
       Runnable add = new AddRunnable(data);
       Runnable dec = new DecRunnable(data);
        for (int i = 0; i < 2; i++) {
            new Thread(add).start();
            new Thread(dec).start();
        }
    }

    private int j = 0;
    public synchronized void add() {
        j++;
        System.out.println("线程" + Thread.currentThread().getName() + "j为：" + j);
    }

    public synchronized void des() {
        j--;
        System.out.println("线程" + Thread.currentThread().getName() + "j为：" + j);
    }

    public class AddRunnable implements Runnable {

        MyData data;

        public AddRunnable(MyData data) {
            this.data = data;
        }

        @Override
        public void run() {
            data.add();
        }
    }

    public class DecRunnable implements Runnable {

        MyData data;

        public DecRunnable(MyData data) {
            this.data = data;
        }

        @Override
        public void run() {
            data.des();
        }
    }

}
