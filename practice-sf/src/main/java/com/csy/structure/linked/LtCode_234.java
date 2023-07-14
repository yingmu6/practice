package com.csy.structure.linked;

import com.csy.util.ListNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chensy
 * @date 2023/7/10
 */
public class LtCode_234 {

    /**
     * 题目描述：
     * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回true；否则，返回false。
     *
     * 输入：head = [1,2,2,1]
     * 输出：true
     *
     * 输入：head = [1,2]
     * 输出：false
     */

    @Test
    public void test_palindrome() {
        System.out.println("是否是回文_V1：" + isPalindrome_V1(createListNode()));
        System.out.println("是否是回文_V2：" + isPalindrome_V2(createListNode()));
        System.out.println("是否是回文_V3：" + isPalindrome_V3(createListNode()));
    }

    private ListNode createListNode () {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        return node1;
    }

    /**
     * 方式一： 将值复制到数组中后用双指针法
     */
    public boolean isPalindrome_V1(ListNode head) {
        List<Integer> vals = new ArrayList<Integer>();

        // 将链表的值复制到数组中
        ListNode currentNode = head;
        while (currentNode != null) {
            vals.add(currentNode.val);
            currentNode = currentNode.next;
        }

        // 使用双指针判断是否回文
        int front = 0;
        int back = vals.size() - 1;
        while (front < back) {
            if (!vals.get(front).equals(vals.get(back))) {
                return false;
            }
            front++;
            back--;
        }
        return true;
    }

    /**
     * 方式二：递归
     */
    public boolean isPalindrome_V2(ListNode head) {
        frontPointer = head;
        return recursivelyCheck(head);
    }

    private ListNode frontPointer;

    private boolean recursivelyCheck(ListNode currentNode) {
        if (currentNode != null) {
            if (!recursivelyCheck(currentNode.next)) {
                return false;
            }
            if (currentNode.val != frontPointer.val) {
                return false;
            }
            frontPointer = frontPointer.next;
        }
        return true;
    }

    /**
     * 方式三：快慢指针
     */
    public boolean isPalindrome_V3(ListNode head) {
        if (head == null) {
            return true;
        }

        // 找到前半部分链表的尾节点并反转后半部分链表
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // 判断是否回文
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 还原链表并返回结果
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

}
