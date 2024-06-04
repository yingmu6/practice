package com.csy.interview.written_exam.collection;

import com.csy.interview.written_exam.collection.map_ext.LRU;
import org.junit.Test;

/**
 * @author chensy
 * @date 2023/8/24
 */
public class LinkedHashMapTest { //@MsY-Doing

    /**
     * 知识点：LinkedHashMap
     *
     * 知识点概括：
     * 1）Hash table and linked list implementation of the Map interface, with predictable（可预料的） iteration（迭代） order.
     *   This implementation differs from HashMap in that it maintains a doubly-linked list（双向列表） running through all of its entries
     *
     * 2）removeEldestEntry方法：可以重写该方法，满足条件即移除最老的数据（该方法会在put或putAll时判断）
     *
     * 3）afterNodeAccess方法：如果LinkedHashMap构造时，指定accessOrder=true，即按访问数据排序，则get操作时，会把当前访问的数据放在队首部，也就是为最活跃的数据
     *
     * 4）LinkedHashMap继承了HashMap，在put或putAll操作后，会调用afterNodeInsertion方法处理插入后操作（HashMap中afterNodeInsertion实现为空，由具体子类来实现）
     *
     * 关联点学习：
     * 1）
     */

    /**
     * 场景1：使用LinkedHashMap实现LRU缓存策略
     * 1）LRU是Least Recently Used的缩写，表示最近最少使用，它是一种缓存策略。
     *    使用场景：当缓存有大小限制或数据量比较大的时候，就无法把所有数据都放在缓存中，因此需要一种策略把缓存的数据部分置换出来
     *
     * 2）LRU的思路：
     *    a）新访问的数据插入到缓存队列中
     *    b）当有新数据要加入到缓存中，但缓存已满，就需要淘汰队尾数据
     *    c）如果缓存中的数据被再次访问，则将数据移到队列首部
     */
    @Test
    public void test_lru_strategy() { //Doing
        LRU<Integer, Integer> lru = new LRU<>(5);
        for (int i = 0; i < 5; i++) {
            lru.put(i, i);
        }

        System.out.println(lru);
        lru.get(3);
        System.out.println(lru);
        lru.put(5, 5);
        System.out.println(lru);

        /**
         * 输出结果：
         * 0->1->2->3->4->
         * 0->1->2->4->3->
         * 1->2->4->3->5->
         *
         * 结果分析：
         * 1）数据没有超过操作限制，即重写的removeEldestEntry方法中size() > LRU.this.cacheSize;比较逻辑，所以能正常插入
         * 2）get操作时，若构造LinkedHashMap时，指定的accessOrder=true，则会把访问的数据移动到队首部，表明为最近常用数据
         * 3）put或put操作时，会调用removeEldestEntry判断是否操作缓存大小，操作则移除队尾数据
         *
         * 结果总结：
         * 1）accessOrder=true移动时，因为LinkedHashMap#Entry维护着before、after，即前后节点，所以移动节点改变这两个节点指向即可
         */
    }
}
