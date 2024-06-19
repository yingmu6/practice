package relative.basic.map_test.treemap;

import java.util.TreeMap;

/**
 * @author : chensy
 * Date : 2020/8/24 上午10:13
 */
public class TreeMapTest {
    public static void main(String[] args) {
        TreeMap<String, String> treeMap = new TreeMap();
        treeMap.put("name", "zhangsan");
        System.out.println(treeMap.get("name"));
    }

    /**
     * 1) TreeMap<K,V>  extends AbstractMap<K,V> implements NavigableMap<K,V>, Cloneable, java.io.Serializable
     * 2) abstract class AbstractMap<K,V> implements Map<K,V>
     * 3) NavigableMap<K,V> extends SortedMap<K,V>
     * 与HashMap相比，TreeMap是一个能比较元素大小的Map集合，会对传入的key进行了大小排序。其中，可以使用元素的自然顺序，也可以使用集合中自定义的比较器来进行排序
     */
}
