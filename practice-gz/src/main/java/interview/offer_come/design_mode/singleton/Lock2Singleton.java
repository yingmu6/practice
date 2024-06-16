package interview.offer_come.design_mode.singleton;

/**
 * @author chensy
 * @date 2024/3/14
 */
public class Lock2Singleton {
    /**
     * 双重校验锁
     */
    private volatile static Lock2Singleton singleton;  //对象锁

    private Lock2Singleton(){}

    public static Lock2Singleton getSingleton() {
        if (singleton == null) {
            synchronized (Singleton.class) { //synchronized方法锁
                if (singleton == null) {
                    singleton = new Lock2Singleton();
                }
            }
        }
        return singleton;
    }
}
