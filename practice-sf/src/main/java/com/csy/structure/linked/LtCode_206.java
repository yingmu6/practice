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
     * 场景1：使用迭代_实现链表反转 OK
     */
    public ListNode reverse_by_iterator(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next; // 1）使用临时节点存储之前链表指向的值，避免后面被更改了（为了下次循环使用）
            curr.next = prev; // 2）当前节点的指针域，指向上一个节点

            prev = curr; // 3）更改上一个节点
            curr = next; // 4）更改当前节点
        }
        return prev;

        /**
         *
         * 代码分析：
         * 1）代码1）和4）处，是为了依次取出链表中结点
         * 2）代码2）让当前节点的地址域指向上一个节点
         * 3）代码3）更换前一个节点，代prev=curr值更新后，并不影响curr.next的值
         * 4）代码3）和4）处，是为了改变变量的值
         *
         * 数值分析：
         * 第一轮循环：
         * 代码1）：next = node2（val=2，next=node3），取原来的第2个节点，下次循环使用
         * 代码2）：curr.next = null（因为prev=null）
         * 代码3）：prev = curr（{val=1，next=null}），即指向原来的第一个节点，但此时next已经变为null了
         * 代码4）：curr = next（next是代码1）中的值，即处理好了第1个节点的next指向，接着处理下面节点的next指向）
         *
         * 第二轮循环：
         * 代码1）：next = node3（val=3，next=node4），取原来的第3个节点，下次循环使用
         * 代码2）：curr.next = prev（{val=1，next=null}）
         * 代码3）：prev = curr（{val=2，next={val=1,next=null}}）
         * 代码4）：curr = next（next是代码1）中的值，即处理好了第2个节点的next指向，接着处理下面节点的next指向）
         *
         * 第3轮循环：
         * 代码1）：next = node4（val=3，next=node4），取原来的第4个节点，下次循环使用
         * 代码2）：curr.next = prev（{val=2，next={val=1,next=null}}）
         * 代码3）：prev = curr（{val=3，next={val=2，next={val=1,next=null}}}）
         * 代码4）：curr = next（next是代码1）中的值，即处理好了第3个节点的next指向，接着处理下面节点的next指向）
         *
         * ...其它类推..
         *
         * 思路总结：
         * 遍历原有链表中的节点，依次将指针域next，指向一个前置节点（前置节点，即为原链表中的上一个节点）
         */
    }

     /**
     * 场景2：使用递归_实现链表反转（递归也是循环中的一种，OK）
     */
    public ListNode reverse_by_recursion(ListNode head) {
        if (head == null || head.next == null) { // 1）函数出口
            return head;
        }
        ListNode newHead = reverse_by_recursion(head.next); // 2）将指针移到尾部，从后面开始处理
        head.next.next = head; // 3）将后面的节点，指向前一个节点
        head.next = null;  // 4）
        return newHead; // 5）函数出口

        /**
         * 代码分析：
         *
         * 数据分析：
         * 第一轮递归：
         * 会一直在2）、1）之间调用，直到head（{val=4, next={val=5,next=null}}）
         * 第一轮递归就是为了指针从头移到尾部，从尾部开始处理
         *
         * 第二轮递归：
         * 1）初始的head为{val=4, next={val=5,next=null}, newHead=reverse_by_recursion(head.next)返回的是{val=5,next=null}
         * 2）head.next.next = head（即将节点{val=5,next=null} 指向前一个节点，即{val=4, next=null}的节点）
         * 3）head.next = null; 将前一个节点的指针域指向空
         * 4) newHead指定的对象，被head.next.next=head改动了，所以此时newHead={val=5, {val=4, next=null}}
         * 5）下一次循环，此时newHead={val=4, next={val=4, next=null}}
         * ......类似上面的循环，也是讲后面节点的指针域，指向前一个节点，以此类推.....
         *
         * 思路总结：
         * 使用递归做了循环处理（看return出口处）
         * 1）第一轮递归，是为了将指针移到最后，从后面节点开始处理
         * 2）第二轮递归，是为了依次更改节点中指针域，将后一个节点，指向前一个节点
         */
    }

    /**
     * 场景3：使用栈_实现链表反转 OK
     */
    public ListNode reverse_by_stack(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode start = new ListNode(0, new ListNode(0));
        ListNode temp = start; //temp和start指向同一个内存地址，temp改变，start也改变
        while (head != null) { // 1）入栈，将每个节点入栈
            stack.push(head);
            head = head.next;
        }
        while (!stack.isEmpty()) { // 2）出栈，将每个节点出栈，并使用一个临时节点来处理指针域
            temp.next = stack.pop();
            temp = temp.next; //进行值代换，即此处等价于 temp = stack.pop().next
        }
        temp.next = null; //最终一个节点（即val=1的节点），next指向null
        return start.next; //去掉头部用于处理的临时节点

        /**
         * 数据分析：
         * 1）代码1）中的循环，是把每个节点放入栈中（还未改动指针域）
         * 2）栈是后进先出，所以弹出栈的元素，会是原有节点后面的元素
         * 3）代码3）的数据分析
         *    a）第一次stack.pop()时，即第一个节点为（val=5, next=null）
         *    b）临时节点的指针域，指向第一个节点
         *    c）temp=temp.next，即temp = stack.pop().next，所以即为{val=5, next=null}的指针域
         *    .....进入下一次循环......
         *    d）第二次stack.pop()时，即第二个节点为（val=4, next=null）
         *    e）此时执行temp.next = stack.pop();时，即将node5 指向 node4
         *    .....后面以此类推......
         *
         * 结果总结：
         * 1）利用栈后进先出的特性，能够将节点入栈后出栈，达到节点内容反转
         * 2）然后在节点出栈时，再依次将后一个节点的指针域指向前一个节点
         * 3）用临时节点temp重新组装链表
         */
    }

    /**
     * 场景4：使用头插法_实现链表反转（头插法待了解）
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
