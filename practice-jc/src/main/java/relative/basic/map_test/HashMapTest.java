package relative.basic.map_test;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chensy
 * @date 2023/6/21
 */
public class HashMapTest {

    /**
     * HashMap_概述
     * 1）HashMap is a map. A map is a key-value mapping, which means that every key is mapped to exactly one value and
     *   that we can use the key to retrieve the corresponding value from a map.
     *  （HashMap是一种Map。Map是key-value映射，能使用key从Map提取value）
     *
     * 2）Why do we need a HashMap? The simple reason is performance. If we want to find a specific element in a list,
     *    the time complexity is O(n) and if the list is sorted, it will be O(log n) using, for example, a binary search
     *  （为什么需要HashMap？这个简单的理由就是由于性能，列表查询是O(n)，HashMap查询是 O(log n) ）
     *
     * 参考链接：
     * 1）https://www.baeldung.com/java-hashmap  HashMap简介
     * 2）com.csy.interview.no1.MapValueTest 类中已做了部分测试
     */

    /**
     * 场景1：基本使用
     */
    @Test
    public void test_basic() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "zhang");
        map.put("age", 18);

        System.out.println(map.get("name"));
        System.out.println(map.get("age"));
    }

    /**
     * 场景2：HashMap的部分特性
     * 1）key值为Null：允许key为Null
     * 2）key值相同：值会覆盖，为最新的值
     * 3）containsKey：检查key是否存在
     * 4）forEach：java8方式遍历
     * 5）getOrDefault()：获取值（未取到值时，返回取默认值）
     * 6）putIfAbsent()：见名知意，即Map中不存在指定键时，才会设置值
     * 7）merge()：当Map中有相同的键时，可以使用merge方法，自定义二元函数，提供合并策略，对值进行合并（值的类型不限定，可以是基本类型，也可以是列表类型）
     * 8）compute()：见名知意，就是对指定键对应的值，自定义二元函数，提供计算策略，对值进行重新计算
     */
    @Test
    public void test_hashMap() {
        Map<String, String> testMap = new HashMap<>();

        // key为Null
        testMap.put(null, "test");
        Assert.assertTrue(testMap.get(null).equals("test"));
        testMap.clear();

        // key相同
        testMap.put("name", "zhang");
        testMap.put("name", "lisi");
        Assert.assertTrue(testMap.get("name").equals("lisi"));
        testMap.clear();

        // 检查key是否存在
        testMap.put("age", "11");
        Assert.assertTrue(testMap.containsKey("age"));
        testMap.clear();

        // java8方式遍历
        testMap.put("name", "wang");
        testMap.put("age", "15");
        testMap.forEach((key, value) -> System.out.println("Key:" + key + ", Value:" + value));
        testMap.clear();

        // getOrDefault()
        System.out.println(testMap.getOrDefault("name2", "liu"));

        /**
         * putIfAbsent()
         *
         * 输出结果：
         * putIfAbsent后的HashMap:{name=liu2, age=22}
         *
         * 结果分析
         * 1）putIfAbsent的功能用途是：在Map中不存在key才会设置值，存在值时返回原来的值
         * putIfAbsent时，已经存在name的键，所以不会设置新的值。而age对应的key不存在，所以会设置该值
         */
        testMap.put("name", "liu2");
        testMap.putIfAbsent("name", "list3");
        testMap.putIfAbsent("age", "22");
        System.out.println("putIfAbsent后的HashMap:" + testMap);
        testMap.clear();

        /**
         * merge方法
         *
         * 输出结果：
         * merge后的HashMap:{name=[zhang, lisi]}
         *
         * 结果分析：
         * 值类型为List<String>，合并策略为：对列表进行addAll操作
         */
        Map<String, List<String>> mergeMap = new HashMap<>();
        mergeMap.put("name", Lists.newArrayList("zhang"));
        mergeMap.merge("name", Lists.newArrayList("lisi"), (valueList1, valueList2) -> { //使用二元函数，对值进行合并（会推导出值的类型为List<String>）
            valueList1.addAll(valueList2);
            return valueList1;
        });
        System.out.println("merge后的HashMap:" + mergeMap);
        mergeMap.clear();

        /**
         * merge方法
         *
         * 输出结果：
         * merge后的HashMap2:{name=zhang,list}
         *
         * 结果分析：
         * 值类型为String，合并策略为：对字符串进行拼接操作
         */
        Map<String, String> mergeMap2 = new HashMap<>();
        mergeMap2.put("name", "zhang");
        mergeMap2.merge("name", "list", (value1, value2) -> value1 + "," + value2);
        System.out.println("merge后的HashMap2:" + mergeMap2);
        mergeMap2.clear();

        /**
         * compute方法
         *
         * 输出结果：
         * computeMap后的HashMap:{score=35}
         *
         * 结果分析：
         * 自定义的二元函数，计算值的策略为：将原有的值加上20
         */
        Map<String, Integer> computeMap = new HashMap<>();
        computeMap.put("score", 15);

        computeMap.compute("score", (key, value) -> value + 20);
        System.out.println("computeMap后的HashMap:" +computeMap);
    }
}
