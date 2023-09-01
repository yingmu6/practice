package com.csy.structure.linked;

import com.csy.structure.linked.ext.MyLinkedList;
import org.junit.Test;

/**
 * 链表测试
 * @author : chensy
 * Date : 2020-03-07 22:57
 */
public class LinkedListTest {

    /**
     * 链表_概要
     *
     * 1）数组和链表都是线性存储结构的基础，栈和队列都是线性存储结构的应用
     *
     * 2）链表是离散存储线性结构。
     *    a）单向链表：一个节点指向下一个节点
     *    b）双向链表：一个节点有两个指针域
     *    c）循环链表：能通过任何一个节点找到其他所有的节点，将两种(双向/单向)链表的最后一个结点指向第一个结点从而实现循环
     *
     * 3）链表分为数据域和指针域
     *    区分node = tempNode、node.next = tempNode的区别
     *    a）node = tempNode，是对象赋值，表明两个对象的引用相同，指向同一块内存地址
     *    b）node.next = tempNode，表明node的指针域，指向tempNode引用
     *
     * 4）递归和迭代的区别
     *   递归中一定有迭代,但是迭代中不一定有递归,大部分可以相互转换.能用迭代的不用递归,递归调用函数,浪费空间,并且递归太深容易造成堆栈的溢出
     *   递归和迭代都是循环的一种（重点理解）
     *   简单地说，递归是重复调用函数自身实现循环。迭代是函数内某段代码实现循环，而迭代与普通循环的区别是：循环代码中参与运算的变量同时是保存结果的变量，当前保存的结果作为下一次循环计算的初始值
     *
     * 5）需要了解对象和引用的关系
     *    a）A a1 = new A(); 创建对象语句理解：
     *      它代表A是类，a1是引用，a1不是对象，new A()才是对象，a1引用指向new A()这个对象。
     *    b）Java中的引用实质就是一个指针，里面存的不是一个对象，而是对象的地址。通过引用可以访问和操作对象、
     *
     * 总结：
     *    a）链表中的指针域，不过就是嵌套的引用罢了，只要把具体的引用以及对象实际值分析清楚即可。
     *     （链表最核心的就是 处理指针域。java中的引用，相当C、C++的指针）
     *    b）画出链表图，包含数据域、指针域、还是node1、node2等等节点序号，并分析出next指针域代码那个节点，比如head.next.next，先分析出head.next为哪个节点
     *
     * 参考链接：
     * a）https://www.jianshu.com/p/73d56c3d228c 链表的数据结构
     * b）https://cloud.tencent.com/developer/article/2095781?areaSource=106005.13 递归和迭代的区别
     * c）https://zhuanlan.zhihu.com/p/30141170 链表测试用例
     * d）https://segmentfault.com/a/1190000022391398 理解Java对象和引用
     * e）https://blog.csdn.net/joob000/article/details/81196165 尾插法和头插法
     */

    /**
     * 场景1：单项列表的增删改查
     */
    @Test
    public void test_linked_with_add() { //单链表添加元素
        System.out.println("添加节点");
        MyLinkedList<String> list = initLinked();
        for(int i = 0; i < list.size(); i++){
            System.out.println("第" + (i + 1) + "节点为：" + list.GetE(i));
        }

        /**
         * 输出结果：
         *
         * 添加节点
         * 第1节点为：one
         * 第2节点为：two
         * 第3节点为：three
         * 第4节点为：four
         *
         * 结果分析：
         *
         */
    }

    @Test
    public void test_linked_with_update() { //单链表修改元素
        MyLinkedList<String> list = initLinked();
        System.out.println("修改节点");
        list.Renew("HaHa", 2);
        for(int i = 1 ; i <= list.size(); i++){
            System.out.println("第" + (i + 1) + "节点为：" + list.GetE(i));
        }

        /**
         * 输出结果：
         *
         * 结果分析：
         */
    }

    @Test
    public void test_linked_with_delete() { //单链表删除元素
        MyLinkedList<String> list = initLinked();
        System.out.println("删除节点");
        System.out.println("删除了第一个节点：" + list.Delete(0));
        for(int i = 0; i <= list.size(); i++){
            System.out.println("第" + (i + 1) + "节点为：" + list.GetE(i));

        }

        /**
         * 输出结果：
         *
         * 结果分析：
         */
    }

    @Test
    public void test_linked_with_find() { //单链表查找元素
        MyLinkedList<String> list = initLinked();
        System.out.println("查找节点");
        System.out.println("第4个节点为：" + list.GetE(3));

        /**
         * 输出结果：
         *
         * 结果分析：
         */
    }

    private MyLinkedList initLinked() { //初始化链表（供上面增删改查用例使用）
        MyLinkedList<String> list = new MyLinkedList();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        return list;
    }

    /**
     * 场景n：头插法
     */

    /**
     * 场景n+1：尾插法
     */

}
