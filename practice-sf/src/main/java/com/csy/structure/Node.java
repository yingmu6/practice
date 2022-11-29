package com.csy.structure;

/**
 * 节点
 * @author : chensy
 * Date : 2020-03-07 22:58
 */
public class Node {
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
