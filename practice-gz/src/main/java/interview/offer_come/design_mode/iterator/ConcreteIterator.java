package interview.offer_come.design_mode.iterator;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class ConcreteIterator implements Iterator {

    private Collection collection;

    private int pos = -1;

    public ConcreteIterator(Collection collection) {
        this.collection = collection;
    }

    @Override
    public Object previous() {
        if (pos > 0) {
            pos--;
        }
        return collection.get(pos);
    }

    @Override
    public Object next() {
        if (pos < collection.size() - 1) {
            pos++;
        }
        return collection.get(pos);
    }

    @Override
    public boolean hasNext() {
        if (pos < collection.size() - 1) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Collection collection1 = new ListCollection();
        collection1.add("object1");
        Iterator it = collection1.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        /**
         * 输出结果：
         * object1
         *
         * 结果分析：
         *
         * 问题点答疑：
         */
    }
}
