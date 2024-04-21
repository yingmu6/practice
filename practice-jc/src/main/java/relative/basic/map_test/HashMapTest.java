package relative.basic.map_test;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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

        /**
         * 输出结果：
         * zhang
         * 18
         *
         * 结果分析
         *
         * 结果概括：
         * 1）阈值 = 负载因子 * 容量，table数组按2倍扩容（HashMap是Node数组+链表的组合）
         * 2）HashMap维护者Node<K,V>[] table;节点数组，节点Node又包含
         *    final int hash;
         *    final K key;
         *    V value;
         *    Node<K,V> next;
         */
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

    /**
     * 场景3：ConcurrentHashMap使用
     * 1）The ConcurrentHashMap operations are thread-safe. ConcurrentHashMap doesn’t allow null for keys and values.
     *   （ConcurrentHashMap是线程安全的，不允许Null的键和值）
     *
     *
     *
     * 参考链接：
     * a）https://www.digitalocean.com/community/tutorials/concurrenthashmap-in-java
     * b）https://juejin.cn/post/7064061605185028110 ConcurrentHashMap原理
     */
    @Test
    public void test_concurrentHashMap_iterator() { //测试ConcurrentHashMap在循环时修改元素
        //ConcurrentHashMap
        Map<String,String> myMap = new ConcurrentHashMap<String,String>();
        myMap.put("1", "1");
        myMap.put("2", "1");
        myMap.put("3", "1");
        myMap.put("4", "1");
        myMap.put("5", "1");
        myMap.put("6", "1");
        System.out.println("ConcurrentHashMap before iterator: "+myMap);
        Iterator<String> it = myMap.keySet().iterator();

        while(it.hasNext()){
            String key = it.next();
            if(key.equals("3")) myMap.put(key+"new", "new3");
        }
        System.out.println("ConcurrentHashMap after iterator: "+myMap);

        /**
         * 输出结果：
         * ConcurrentHashMap before iterator: {1=1, 2=1, 3=1, 4=1, 5=1, 6=1}
         * ConcurrentHashMap after iterator: {1=1, 2=1, 3=1, 4=1, 5=1, 3new=new3, 6=1}
         *
         * 结果分析：
         * ConcurrentHashMap在循环时，是可以修改元素的
         */

    }

    /**
     * HashMap在迭代时，修改元素，报异常的原因:（因为HashMap的迭代器设计不支持）
     * 1）在使用HashMap进行遍历和删除操作时，不能在遍历过程中直接删除元素，这是因为HashMap的迭代器设计不支持在遍历时对集合进行结构性修改。
     *    当在遍历过程中直接删除元素时，会导致迭代器的状态与实际集合的状态不一致，可能引发ConcurrentModificationException(并发修改异常)
     *
     * 2）具体来说，当创建HashMap的迭代器时，会生成一个"modCount"字段，表示HashMap结构性修改的次数。每当对HashMap进行插入、删除等操作时，"modCount"都会增加。
     *    而在迭代器遍历HashMap时，会将当前的"modCount"与之前保存的"expectedModCount"进行比较，如果两者不相等，则会抛出ConcurrentModificationException
     *
     * 参考链接：
     * https://www.cnblogs.com/jingzh/p/17094607.html
     * https://www.itheima.com/news/20230705/093624.html
     */
    @Test
    public void test_hashMap_iterator() { //测试HashMap在循环时修改元素（会抛出ConcurrentModificationException异常）

        try {
            //HashMap
            Map myMap = new HashMap<String, String>();
            myMap.put("1", "1");
            myMap.put("2", "1");
            myMap.put("3", "1");
            myMap.put("4", "1");
            myMap.put("5", "1");
            myMap.put("6", "1");
            System.out.println("HashMap before iterator: " + myMap);
            Iterator<String> it1 = myMap.keySet().iterator();

            while (it1.hasNext()) {
                String key = it1.next();
                if (key.equals("3")) myMap.put(key + "new", "new3");
            }
            System.out.println("HashMap after iterator: " + myMap);
        } catch (ConcurrentModificationException e) {
            System.out.println("执行时异常");
        }
    }
}
