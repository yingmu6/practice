package relative.basic.interface_test;

/**
 * @author chensy
 * @date 2021/2/6
 */
public interface Fruits {
    double getWeight();
    default double getSize() { //实现类可以选择重写default方法，也可以不用实现
        return 223;
    };
}