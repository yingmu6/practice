package com.csy.structure.linked;

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
     * 总结：链表中的指针域，不过就是嵌套的引用罢了，只要把具体的引用以及对象实际值分析清楚即可。
     *     （链表最核心的就是 处理指针域。java中的引用，相当C、C++的指针）
     *
     * 参考链接：
     * a）https://www.jianshu.com/p/73d56c3d228c 链表的数据结构
     *
     */

    // 单向列表
    @Test
    public void test_basic() {
        Node head = new Node();
        head.setData("head");
        Node a = new Node();
        a.setData("A");
        Node b = new Node();
        b.setData("B");

        head.setNext(a);
        a.setNext(b);

        b.setNext(null); //单向链表
//        b.setNext(head); // 循环列表
        printNode(head);
    }

    // 递归调用，输出结果
    private static void printNode(Node node) {
        if (node.getNext() != null) {
            System.out.println(node.getNext().getData());
        }
    }


    class Node {
        protected String data;
        protected Node next;

        public Node() {
        }

        public Node(String data) {
            this.data = data;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

}
