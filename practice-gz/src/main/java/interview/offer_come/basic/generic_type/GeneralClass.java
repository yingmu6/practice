package interview.offer_come.basic.generic_type;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class GeneralClass<T> { //泛型类

    private T t;

    public void add(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }
}
