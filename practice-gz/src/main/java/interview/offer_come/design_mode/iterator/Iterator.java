package interview.offer_come.design_mode.iterator;

/**
 * @author chensy
 * @date 2024/3/15
 */
public interface Iterator {

    Object previous();

    Object next();

    boolean hasNext();
}