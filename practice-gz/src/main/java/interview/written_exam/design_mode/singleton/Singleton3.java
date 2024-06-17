package interview.written_exam.design_mode.singleton;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class Singleton3 { //@MsY-Doing

    /**
     * 知识点：单例模式之 synchronized + 双重判定
     *
     * 知识点概括：
     * 1）懒汉式和饿汉式，在多线程编程下，可以会线程不安全的
     */

    private volatile static Singleton3 singleton;

    private Singleton3() {}

    // 按需实例化
    public static Singleton3 getInstance() {
        if (singleton == null) {
            synchronized (Singleton3.class) {
                if (singleton == null) {
                    singleton = new Singleton3();
                }
            }
        }
        return singleton;
    }
}
