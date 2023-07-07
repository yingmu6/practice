package relative.thread;

import org.junit.Test;

/**
 * @author chensy
 * @date 2023/7/7
 */
public class UnSafeTest {

    /**
     *计数器
     */
    private static int num = 0;

    /**
     *静态方法，直接对num计数器进行++操作。
     */
    public  static void inCreate() { //线程不安全
        num++;
    }

    public  synchronized static void inCreateV2() { //线程安全，加锁处理
        num++;
    }

    /**
     * 场景1：
     * 参考链接：
     * a）https://blog.csdn.net/BCDMW233/article/details/90653867
     * b）https://blog.csdn.net/weixin_46713508/article/details/126755984 现成不安全用例
     */
    @Test
    public void test_unsafe() {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 100; j++) {
                    inCreate();
                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        System.out.println(num);

        /**
         * 输出结果：
         * 值不确定，如800、900、871等
         *
         * 结果分析：
         */
    }

    @Test
    public void test_safe() {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 100; j++) {
                    inCreateV2();
                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        System.out.println(num);
    }
}
