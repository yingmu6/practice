package com.csy.interview.no1;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chensy
 * @date 2023/7/3
 */
public class MapValueTest {

    /**
     * Map_概述
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
    public void test_map_value() {
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
         * 结果不确定，主要是System.currentTimeMillis()值，这个是对应毫秒值。因为现在的计算机运行速度比较快
         * 所以这个值可能会相等，而Map中相同key时，就会出现值覆盖。所以直接运行时会输出3，debug有停顿时，会输出1、2、3
         */
    }

    /**
     * 场景2：HashMap的部分特性
     * 1）key值为Null：允许key为Null
     * 2）key值相同：值会覆盖，为最新的值
     * 3）检查key是否存在
     * 4）java8方式遍历
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
