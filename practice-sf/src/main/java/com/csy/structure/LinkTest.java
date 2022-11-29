package com.csy.structure;

/**
 * 链表测试 https://www.jianshu.com/p/73d56c3d228c 链表的数据结构
 * https://www.cnblogs.com/alsf/p/5520266.html  单向列表的实现
 * @author : chensy
 * Date : 2020-03-07 22:57
 */
public class LinkTest {
    public static void main(String[] args) {
        singleListV2();
    }

    // 单向列表
    private static void singleListV2() {
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
        System.out.println(node.getData());
//        if (node.getNext().getData() != "head") {
//            printNode(node.getNext());
//        }

        if (node.getNext().getData() != null) {
            printNode(node.getNext());
        }
    }

    /**
     * https://juejin.im/post/6844903584027377677
     * 数组和链表都是线性存储结构的基础，栈和队列都是线性存储结构的应用
     * 链表是离散存储线性结构。
     * 单向链表：一个节点指向下一个节点
     * 双向链表：一个节点有两个指针域
     * 循环链表：能通过任何一个节点找到其他所有的节点，将两种(双向/单向)链表的最后一个结点指向第一个结点从而实现循环
     */
}
