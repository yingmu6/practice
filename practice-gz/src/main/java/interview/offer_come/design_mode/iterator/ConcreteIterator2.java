package interview.offer_come.design_mode.iterator;

import java.util.Iterator;

/**
 * @author orange
 * @date 2024/7/11
 */
public class ConcreteIterator2 implements Iterator {

    private int pos = -1;

    private Collection collection;

    public ConcreteIterator2(Collection collection) {
        this.collection = collection;
    }

    @Override
    public boolean hasNext() {
        if (pos < collection.size() - 1) {
            return true;
        }
        return false;
    }

    @Override
    public Object next() {
        if (pos < collection.size() - 1) {
            pos++;
        }
        return collection.get(pos);
    }

    /**
     * 新增场景：forEach语句中使用迭代器
     */
    public static void main(String[] args) {

    }
}
