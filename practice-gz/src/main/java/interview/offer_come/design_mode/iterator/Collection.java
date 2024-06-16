package interview.offer_come.design_mode.iterator;

/**
 * @author chensy
 * @date 2024/3/15
 */
public interface Collection {

    Iterator iterator();

    Object get(int i);

    boolean add(Object object);

    int size();
}