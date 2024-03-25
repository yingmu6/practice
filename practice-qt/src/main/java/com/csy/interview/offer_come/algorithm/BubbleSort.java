package com.csy.interview.offer_come.algorithm;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class BubbleSort {

    /**
     * 冒泡排序：
     *
     * 测试用例：
     * 初始数组：[4,5,6,3,2,1]
     * 第1趟：[4,5,3,2,1,6]
     * 第2趟：[4,3,2,1,5,6]
     * 第3趟：[3,2,1,4,5,6]
     * 第4趟：[2,1,3,4,5,6]
     * 第5趟：[1,2,3,4,5,6]
     */
    public static int[] sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }
}
