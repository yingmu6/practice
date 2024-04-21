package thinking.collection_deep;

import net.mindview.util.CountingMapData;

import java.util.HashMap;
import java.util.Map;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * @author chensy
 * @date 2024/4/18
 */
public class Maps { //@thinking Done

    /**
     * 知识点：性能
     */

    public static void printKeys(Map<Integer, String> map) {
        printnb("Size = " + map.size() + "，");
        printnb("Keys：");
        print(map.keySet());
    }

    public static void test(Map<Integer, String> map) {
        print(map.getClass().getSimpleName());
        map.putAll(new CountingMapData(25));
        map.putAll(new CountingMapData(25));
        printKeys(map);
        printnb("Values：");
        print(map.values());
        print(map);
        print("map.containsKey(11)：" + map.containsKey(11));
        print("map.get(11)：" + map.get(11));
        print("map.containsValue(\"F0\")："
        + map.containsKey("F0"));
        Integer key = map.keySet().iterator().next();
        print("First key in map：" + key);
        map.remove(key);
        printKeys(map);
        map.clear();
        print("map.isEmpty()：" + map.isEmpty());
        map.putAll(new CountingMapData(25));
        map.keySet().removeAll(map.keySet());
        print("map.isEmpty()：" + map.isEmpty());
    }

    public static void main(String[] args) {
        test(new HashMap<>());
//        test(new TreeMap<>());
//        test(new LinkedHashMap<>());
//        test(new IdentityHashMap<>());
//        test(new ConcurrentHashMap<>());
//        test(new WeakHashMap<>());

        /**
         * 输出结果：
         *
         * 结果分析：
         *
         * 结果概括：
         */
    }
}
