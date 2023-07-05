package com.csy.util;

/**
 * @author chensy
 * @date 2023/7/5
 */
public class ListNode { //公共列表节点

    public int val;
    public ListNode next;

    ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
