package interview.offer_come.basic.generic_type;

/**
 * @author chensy
 * @date 2024/3/16
 */
public interface IGeneral<T> { //泛型接口

    void setId(T t);

    T getId();
}