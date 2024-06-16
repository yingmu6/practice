package interview.written_exam.collection.map_ext;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author chensy
 * @date 2023/8/24
 */
public class LRU<K, V> {

    private static final float hashLoadFactory = 0.75f;
    private LinkedHashMap<K, V> map;
    private int cacheSize;

    public LRU(int cacheSize) {
        this.cacheSize = cacheSize;
        int capacity = (int)Math.ceil(cacheSize / hashLoadFactory) + 1; //ceil：向上取整（此处的cacheSize相当于Map的阈值threshold，）
        map = new LinkedHashMap<K, V>(capacity, hashLoadFactory, true) { //匿名内部类：LinkedHashMap第三个参数为访问顺序accessOrder，为true表示按访问排序，否则按插入排序
            private static final long serialVersionUID = 1L;

            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) { //重写移除最老数据的条件，在put或putAll会调用
                return size() > LRU.this.cacheSize;
            }
        };
    }

    public synchronized V get(K key) {
        return map.get(key);
    }

    public synchronized void put(K key, V value) {
        map.put(key, value);
    }

    public synchronized void clear() {
        map.clear();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("");
        for (Map.Entry<K, V> entry : map.entrySet()) {
            sb.append(entry.getValue() + "->");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int cacheSize = 5;
        int capacity = (int)Math.ceil(cacheSize / hashLoadFactory) + 1;
        System.out.println(capacity);

        /**
         * 输出结果：
         * 8
         *
         * 结果分析：
         * 因为 5/0.75 = 6.3333，通过cell向上取整为7，所以最终结果为8
         */
    }
}
