package com.csy.algorithm.recurtion;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * @author chensy
 * @date 2023/6/30
 */
public class LtCode_21 {

    /**
     * 题目描述：
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     *
     * 示例 1：
     * 输入：l1 = [1,2,4], l2 = [1,3,4]
     * 输出：[1,1,2,3,4,4]
     *
     * 示例 2：
     * 输入：l1 = [], l2 = []
     * 输出：[]
     *
     * 示例 3：
     * 输入：l1 = [], l2 = [0]
     * 输出：[0]
     */


    /**
     * 场景1：先用数组模拟算法逻辑
     */
    @Test
    public void test_mergeArray() {
        int[] arr1 = new int[] {0, 2, 4, 6};
        int[] arr2 = new int[] {1, 3, 5, 7};

        System.out.println(JSON.toJSONString(mergeTwoArrays(arr1, arr2)));
    }

    private static int FLAG = -111;

    public int[] mergeTwoArrays(int[] left, int[] right) {
        int[] mergeArrays = new int[left.length + right.length];
        for (int n = 0; n < mergeArrays.length; n++) { //将最终合成的数组进行初始化（设置指定标志的值，能够区分出是否处理）
            mergeArrays[n] = FLAG;
        }

        int k = 0;
        for (int i = 0; i < left.length; i++) { //依次变量左边的数组
            int temp = left[i];
            boolean isExist = false;
            for (int j = 0; j < right.length; j++) {
                if (right[j] < temp && right[j] != FLAG) { //找出比左边小的元素
                    temp = right[j]; //若找到比左边小的元素，则需要进行位置交换
                    right[j] = FLAG;
                    isExist = true;
                    break;
                }
            }
            mergeArrays[k] = temp;
            if (isExist) {
                mergeArrays[k + 1] = left[i]; //找到比左边小的元素，把小的元素放在左边
                k = k + 2;
            } else {
                k++;
            }
        }

        for (int j = 0; j < right.length; j++) { //左边的数据遍历完以后，可能右边的数据还没有遍历完，所以要将未遍历完的元素筛选出来，放在最终的数组中
            if (right[j] != FLAG) {
                for (int m = 0; m < mergeArrays.length; m++) {
                    if (mergeArrays[m] == FLAG) {
                        mergeArrays[m] = right[j];
                    }
                }
            }
        }

        return mergeArrays;
    }

    @Test
    public void test_mergeList() {
        ListNode tail1 = new ListNode(4);
        ListNode middle1 = new ListNode(2, tail1);
        ListNode head1 = new ListNode(1, middle1);

        ListNode tail2 = new ListNode(4);
        ListNode middle2 = new ListNode(3, tail2);
        ListNode head2 = new ListNode(1, middle2);

        ListNode tempNode = mergeTwoLists(head1, head2);
        while (tempNode != null) {
            System.out.println(tempNode.val);
            tempNode = tempNode.next;
        }
    }

    /**
     * 场景2：使用递归合并两个列表
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) { //递归终止条件
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) { //l1小于l2的值，就取出l1的值，并且l1的next指向合并的节点
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else { //l1大于l2的值，就取出l2的值，并且l2的next指向合并的节点
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
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
