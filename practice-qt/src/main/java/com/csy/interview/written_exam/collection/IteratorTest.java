package com.csy.interview.written_exam.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author chensy
 * @date 2023/9/1
 */
public class IteratorTest { //@MsY-Doing

    /**
     * 知识点：Iterator迭代器
     *
     * 知识点概括：
     * 1）An Iterator is one of many ways we can traverse（遍历） a collection, and as every option, it has its pros（赞成，优点） and cons（反对，缺点）.
     *
     * 2）Iterator的特点：
     *    a）introduced improved method names（引进改进的方法名）
     *    b）made it possible to remove elements from a collection we're iterating over (可以在迭代中移除元素)
     *    c）doesn't guarantee iteration order
     *
     * 关联点学习：
     * 1）模拟LinkedList关联的多个类的多个层级继承，看下iterator()的执行流程。
     *
     * 参考链接：
     * a）https://www.baeldung.com/java-iterator
     */

    /**
     * Iterator基本使用
     */
    @Test
    public void test_iterator_basic() { //Doing
        List<String> list = new LinkedList<>(); //向上转型
        list.add("first");
        list.add("second");
        list.add("third");
        list.add("fourth");
        for (Iterator<String> it = list.iterator(); it.hasNext();) {
            String str = (String) it.next(); //此处id类型为：AbstractList的内部类Itr
            System.out.println(str);
        }

        /**
         * 输出结果：
         * first
         * second
         * third
         * fourth
         *
         * 结果分析：
         * 1）Iterator中的hasNext() 判断是否还有元素，it.next() 返回迭代中的下一个元素
         *    从Abstract$Itr的源码分析：
         *    a）hasNext()：将游标和容量比较 cursor != size();
         *    b）next()：获取游标对应的元素 E next = get(i); 且获取后将游标加1：cursor = i + 1;
         *
         * 问题点答疑：
         * 1）为什么此处的list.iterator()执行的是AbstractList的iterator()方法，而不是AbstractSequentiaList的iterator()方法？
         *    从类的继承关系来看：LinkedList -> AbstractSequentiaList -> AbstractList -> AbstractCollection
         */
    }

    /**
     * 场景2：在遍历时，添加元素
     */
    @Test
    public void test_iterator_add() {
        try {
            List<String> list = new LinkedList<>();
            list.add("first");
            list.add("second");
            list.add("third");
            list.add("fourth");

            for (Iterator<String> it = list.iterator(); it.hasNext();) {
                String str = (String) it.next();
                System.out.println(str);
                if (str.equals("second")) { //此处若执行remove操作，也会报异常（可以使用临时的集合，记录下需要处理的元素）
                    list.add("five");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 输出结果：
         * first
         * second
         * java.util.ConcurrentModificationException
         *   	at java.util.LinkedList$ListItr.checkForComodification(LinkedList.java:966)
         * 	    at java.util.LinkedList$ListItr.next(LinkedList.java:888)
         *   	at com.csy.interview.written_exam.collection.IteratorTest.test_iterator_remove(IteratorTest.java:65)
         *
         * 结果分析：
         * 1）在list.add添加元素后，调用it.next()时，会调用ListItr#next方法，会比较列表中当前的数目和预期的数目，若不相等，则抛出ConcurrentModificationException异常
         *         final void checkForComodification() {
         *             if (modCount != expectedModCount)
         *                 throw new ConcurrentModificationException();
         *         }
         *
         * 2）LinkedList中的list.iterator()获取迭代器，是通过AbstractList#listIterator获取的，最后的对象类型为AbstractList的私有内部类ListItr
         *   在获取迭代器的时候，会包容器的个数赋值给exceptionModCount，在调用next()方法时，会比较容器中实际的个数和exceptionModCount，如此处容器的值由于add了一个元素变为5
         *   而exceptionModCount还是最初的容器元素值4，所以就排除异常了
         *
         * 3）解决方法，在Iterator迭代时，不要进行add、remove操作，可以先记到一个集合中，迭代完后，在用集合中的元素来处理
         *   （出现ConcurrentModificationException的问题：
         *      a：在单线程中是由于遍历中操作集合，可以用临时的集合处理
         *      b：在多线程中可能由并发问题产生了，可以用线程安全的容器，如ConcurrentHashMap和CopyOnWriteArrayList等
         *         也可以使用synchronized、lock等加锁，但要注意性能。
         *    ）
         */
    }
}
