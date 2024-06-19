package relative.java8.function;

/**
 * @author chensy
 * @date 2021/9/5
 */
@FunctionalInterface
public interface CheckFunction<T> {
    Boolean check(T t);
}