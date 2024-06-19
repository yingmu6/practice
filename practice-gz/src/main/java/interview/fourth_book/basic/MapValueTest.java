package interview.fourth_book.basic;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chensy
 * @date 2023/7/3
 */
public class MapValueTest { //MsY-Done

    /**
     * 知识点：Map
     *
     * 知识点概括：
     * 1）A Map is an object that maps keys to values. A map cannot contain duplicate keys: Each key can map to at most one value
     *   （Map是一个将键映射到值的对象。Map不能包含重复的键，每个键最多映射一个值）
     *
     * 2）The Java platform contains three general-purpose Map implementations: HashMap, TreeMap, and LinkedHashMap.
     *  （Java平台包含三种通用的Map实现:HashMap、TreeMap和LinkedHashMap）
     *
     * 3）he Map interface includes methods for basic operations 基本操作 (such as put, get, remove, containsKey, containsValue, size, and empty),
     *   bulk operations 批量操作 (such as putAll and clear), and collection views 集合视图 (such as keySet, entrySet, and values).
     *
     * 参考链接：
     * a）https://www.baeldung.com/java-hashmap HashMap介绍
     * b）https://docs.oracle.com/javase/tutorial/collections/interfaces/map.html 官网：Map介绍
     */

    /**
     * 场景1：Map的值测试
     */
    @Test
    public void test_map_value() { //Done
        Map<String, String> map = new HashMap<>();
        map.put(String.valueOf(System.currentTimeMillis()) + "a", "1");
        map.put(String.valueOf(System.currentTimeMillis()) + "a", "2");
        map.put(String.valueOf(System.currentTimeMillis()) + "a", "3");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }

        /**
         * 输出结果：
         * 输出的结果结果，不确定的。
         * 可能只输出3，也可能输出1，2，3
         *
         * 结果分析：
         * 1）结果不确定，主要是System.currentTimeMillis()值，这个是对应毫秒值。因为现在的计算机运行速度比较快
         *   所以这个值可能会相等，而Map中相同key时，就会出现值覆盖。所以直接运行时会输出3，debug有停顿时，会输出1、2、3
         */
    }
}
