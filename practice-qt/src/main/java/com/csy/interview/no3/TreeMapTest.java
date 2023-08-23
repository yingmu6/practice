package com.csy.interview.no3;

import org.junit.Test;

import java.util.*;

/**
 * @author chensy
 * @date 2023/8/23
 */
public class TreeMapTest {

    /**
     * TreeMap_测试
     * 1）TreeMap中的所有的元素保持某种固定的顺序，若需要得到一个有序的结果，就使用TreeMap
     */

    /**
     * 场景1：TreeMap与HashMap比较（关于是否排序场景）
     */
    @Test
    public void test_tree_map() {
        Map<Long, String> map = new HashMap<>();
        map.put(11L, "James");
        map.put(201L,"Paul");
        map.put(51L, "Rose");

        System.out.println("HashMap，无序：");
        for (Iterator<Long> it = map.keySet().iterator();it.hasNext();) {
            Long key = it.next();
            String value = map.get(key);
            System.out.println(key + " " + value);
        }

        System.out.println("TreeMap，升序：");
        TreeMap<Long, String> treeMap = new TreeMap();
        treeMap.putAll(map);
        for (Iterator<Long> it = treeMap.keySet().iterator();it.hasNext();) {
            Long key = it.next();
            String value = treeMap.get(key);
            System.out.println(key + " " + value);
        }

        System.out.println("TreeMap，降序：");
        TreeMap<Long, String> treeMap2 = new TreeMap(Collections.reverseOrder());
        treeMap2.putAll(map);
        for (Iterator<Long> it = treeMap2.keySet().iterator();it.hasNext();) {
            Long key = it.next();
            String value = treeMap2.get(key);
            System.out.println(key + " " + value);
        }

        /**
         * 输出结果：
         * HashMap，无序：
         * 51 Rose
         * 201 Paul
         * 11 James
         * TreeMap，升序：
         * 11 James
         * 51 Rose
         * 201 Paul
         * TreeMap，降序：
         * 201 Paul
         * 51 Rose
         * 11 James
         *
         * 结果分析：
         * 1）HashMap是根据hashCode来快速查找的，没有对Key进行排序
         * 2）TreeMap是使用红黑树实现的，实现了SortedMap接口，是可以对Key排序的，查看put源码了解到，可分为两种情况
         *    a）未指定比较器Comparator的，将添加的元素与map中的元素直接通过compareTo方法比较，然后将接口放在左节点或右节点（默认为升序排序）
         *    b）指定了比较器Comparator的，使用比较器进行比较，即cpr.compare(key, t.key)，然后将接口放在左节点或右节点（比较器可定义升序或降序）
         */
    }
}
