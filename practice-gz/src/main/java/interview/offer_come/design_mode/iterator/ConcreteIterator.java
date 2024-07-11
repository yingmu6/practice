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

    private int pos = -1; //游标位置

    public ConcreteIterator(Collection collection) {
        this.collection = collection;
    }

    @Override
    public Object previous() {
        if (pos > 0) { //取前一个元素时，游标减1
            pos--;
        }
        return collection.get(pos);
    }

    @Override
    public Object next() { //取下一个元素时，游标加1
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
        Collection collection1 = new ListCollection(); //自定义的Collection以及实现类
        collection1.add("ccc");
        collection1.add("aaa");
        collection1.add("bbb");

        Iterator it = collection1.iterator(); //自定义的迭代器
        while (it.hasNext()) {
            System.out.println(it.next());
        }

//        for (String str : it) { //此处报语法错误，此处的自定义迭代器it不能用于此处
//
//        }

        ConcreteIterator2 it2 = new ConcreteIterator2(collection1);
        while (it2.hasNext()) {
            System.out.println(it2.next());
        }

//        for (Object str : it2) { //此处报语法错误，此处的迭代器虽然实现了jdk的iterator，但还是报错
//
//        }


        /**
         * 输出结果：
         * ccc
         * aaa
         * bbb
         * ccc
         * aaa
         * bbb
         *
         * 结果分析：
         * 1）
         *
         */
    }
}
