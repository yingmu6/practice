package interview.offer_come.design_mode.iterator;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class ConcreteIterator implements Iterator {

    /**
     * 知识点：
     *
     * 知识点概括：
     * 1）
     */

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
        collection1.add("ccc");
        collection1.add("aaa");
        collection1.add("bbb");
        Iterator it = collection1.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        /**
         * 输出结果：
         * ccc
         * aaa
         * bbb
         *
         * 结果分析：
         *
         */
    }
}
