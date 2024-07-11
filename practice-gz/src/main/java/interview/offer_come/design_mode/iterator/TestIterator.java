package interview.offer_come.design_mode.iterator;

import java.util.Iterator;

/**
 * @author orange
 * @date 2024/7/11
 */
public class TestIterator implements Iterable {

    @Override
    public Iterator iterator() {
        return new ConcreteIterator2(null); //todo
    }
}
