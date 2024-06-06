package thinking.holder_obj;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author orange
 * @date 2024/6/5
 */
public class AdapterMethodIdiom {

    /**
     * 知识点（11.13.1）：适配器方法惯用法
     */
    static class ReversibleArrayList<T> extends ArrayList<T> {
        public ReversibleArrayList(Collection<T> c) {
            super(c);
        }

        public Iterable<T> reversed() {
            return new Iterable<T>() { //todo

                @Override
                public Iterator<T> iterator() {

                    return null;
                }
            };
        }
    }
}
