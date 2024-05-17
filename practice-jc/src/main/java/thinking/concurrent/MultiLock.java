package thinking.concurrent;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class MultiLock { //@TkY-Doing

    /**
     * 知识点：
     *
     * 知识点概括：
     * 1）
     */

    public synchronized void f1(int count) {
        if (count-- > 0) {
            print("f1() calling f2() with count " + count);
            f2(count); //调用synchronized修饰的f2方法
        }
    }

    public synchronized void f2(int count) {
        if (count-- > 0) {
            print("f2() calling f1() with count " + count);
            f1(count);
        }
    }

    public static void main(String[] args) {
        final MultiLock multiLock = new MultiLock();
        new Thread() {
            public void run() {
                multiLock.f1(10);
            }
        }.start();

        /**
         * 输出结果：
         * f1() calling f2() with count 9
         * f2() calling f1() with count 8
         * f1() calling f2() with count 7
         * f2() calling f1() with count 6
         * f1() calling f2() with count 5
         * f2() calling f1() with count 4
         * f1() calling f2() with count 3
         * f2() calling f1() with count 2
         * f1() calling f2() with count 1
         * f2() calling f1() with count 0
         *
         * 结果分析：
         * 1）f1()、f2()都使用synchronized修饰，方法中互相调用
         *    体现了synchronized的可重入性
         */
    }
}
