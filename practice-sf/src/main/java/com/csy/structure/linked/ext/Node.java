package com.csy.structure.linked.ext;

/**
 * @author chensy
 * @date 2023/8/27
 */
public class Node<E> { //单向链表的节点

    private Node<E> next;// 指向下一个节点

    private E e;// 数据域

    public Node() {

    }

    public Node(E e) {
        this.e = e;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public E getE() {
        return e;
    }

    public void setE(E e) {
        this.e = e;
    }
}
