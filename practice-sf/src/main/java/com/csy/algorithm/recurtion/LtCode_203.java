package com.csy.algorithm.recurtion;

import org.junit.Test;

/**
 * @author chensy
 * @date 2023/7/2
 */
public class LtCode_203 {

    /**
     * 题目描述：
     * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
     *
     * 示例 1：
     * 输入：head = [1,2,6,3,4,5,6], val = 6
     * 输出：[1,2,3,4,5]
     *
     * 示例 2：
     * 输入：head = [], val = 1
     * 输出：[]
     *
     * 示例 3：
     * 输入：head = [7,7,7,7], val = 7
     * 输出：[]
     */
    @Test
    public void test_removeElements() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(6);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(5);
        ListNode node7 = new ListNode(6);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        ListNode tempNode = removeElements(node1, 6);
        while (tempNode != null) {
            System.out.println(tempNode.val);
            tempNode = tempNode.next;
        }
    }

    /**
     * 方式一：使用递归方式求解
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }

    /**
     * 方式二：使用迭代方式求解
     */
    public ListNode removeElementsV2(ListNode head, int val) {
        return null;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
