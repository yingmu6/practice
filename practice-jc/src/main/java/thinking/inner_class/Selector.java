package thinking.inner_class;

/**
 * @author chensy
 * @date 2024/3/15
 */
public interface Selector {
    boolean end();
    Object current();
    void next();
}