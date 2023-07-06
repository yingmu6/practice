package com.csy.structure.linked;

import com.alibaba.fastjson.JSON;
import com.csy.util.ListNode;
import org.junit.Test;

import java.util.Stack;

/**
 * @author chensy
 * @date 2023/7/5
 */
public class LtCode_206 {

    /**
     * 题目描述：
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     *
     * 示例1：
     * 输入：head = [1,2,3,4,5]
     * 输出：[5,4,3,2,1]
     *
     * 示例2：
     * 输入：head = [1,2]
     * 输出：[2,1]
     *
     * 示例3：
     * 输入：head = []
     * 输出：[]
     */
    @Test
    public void test_reverse_list() {


        // 方式一：使用迭代
        ListNode tempNode = reverse_by_iterator(createListNode());
        while (tempNode != null) {
            System.out.println(tempNode.val);
            tempNode = tempNode.next;
        }
        System.out.println("--------分隔线1----------");

        // 方式二：使用递归
        ListNode tempNode2 = reverse_by_recursion(createListNode());
        while (tempNode2 != null) {
            System.out.println(tempNode2.val);
            tempNode2 = tempNode2.next;
        }
        System.out.println("--------分隔线2----------");

        // 方式二：使用栈
        ListNode tempNode3 = reverse_by_stack(createListNode());
        while (tempNode3 != null) {
            System.out.println(tempNode3.val);
            tempNode3 = tempNode3.next;
        }
        System.out.println("--------分隔线3----------");

        // 方式四：使用头插法
        ListNode tempNode4 = reverse_by_head(createListNode());
        while (tempNode4 != null) {
            System.out.println(tempNode4.val);
            tempNode4 = tempNode4.next;
        }
        System.out.println("--------分隔线4----------");
    }

    private ListNode createListNode () {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        return node1;
    }

    /**
     * 场景1：使用迭代_实现链表反转
     */
    public ListNode reverse_by_iterator(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next; // 1）
            curr.next = prev; // 2）
            prev = curr; // 3）
            curr = next; // 4）
        }
        return prev;

        /**
         * 结果分析：
         * 1）代码1）和4）处，是为了依次取出链表中结点
         * 2）代码2）让当前节点的地址域指向上一个节点
         * 3）代码3）更换前一个节点
         */
    }

    /**
     * 场景2：使用递归_实现链表反转
     */
    public ListNode reverse_by_recursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverse_by_recursion(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 场景3：使用栈_实现链表反转
     */
    public ListNode reverse_by_stack(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode start = new ListNode(0, new ListNode(0));
        ListNode temp = start;
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        while (!stack.isEmpty()) {
            temp.next = stack.pop();
            temp = temp.next;
        }
        temp.next = null;
        return start.next;
    }

    /**
     * 场景4：使用头插法_实现链表反转
     */
    public ListNode reverse_by_head(ListNode head) {
        //使用头插法
        ListNode dummy = new ListNode(0);
        ListNode p = dummy, cur = head;
        while(cur != null){
            //从head摘下一个头
            ListNode t = cur;
            cur = cur.next;     //cur移到下一个
            t.next = p.next;    //头插法插入
            p.next = t;
        }
        return dummy.next;
    }



    /**
     * 场景N：使用数组模拟反转
     */
    @Test
    public void test_reverse_by_array() {
        int []arr = new int[] {1,2,3,4,5};
        int []newArr = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            newArr[arr.length - i - 1] = arr[i]; //直接通过数组下标来实现反转
        }
        System.out.println(JSON.toJSONString(newArr));
    }
}
