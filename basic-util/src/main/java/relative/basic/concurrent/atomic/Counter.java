package relative.basic.concurrent.atomic;

/**
 * 使用synchronized实现线程安全
 * @author : chensy
 * Date : 2020-02-16 22:14
 */
public class Counter {
    private int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized int increment() {
        return value++;
    }

    public synchronized int decrement() {
        return --value;
    }
}
