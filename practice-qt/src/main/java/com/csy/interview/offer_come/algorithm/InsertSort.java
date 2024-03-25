package com.csy.interview.offer_come.algorithm;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class InsertSort {

    /**
     * 插入排序
     *
     * 测试用例：
     * 初始数组：[6,2,5,7,8]
     * 第1趟：L子集为[6]，R子集为[2,5,7,8]
     * 第2趟：L子集为[2,6]，R子集为[5,7,8]
     * 第3趟：L子集为[2,5,6]，R子集为[7,8]
     * 第1趟：L子集为[2,5,6,7]，R子集为[8]
     * 第1趟：L子集为[2,5,6,7,8]，R子集为[]
     */
    public static int[] sort(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            int insertVal = arr[i];
            int index = i - 1;
            while (index >= 0 && insertVal < arr[index]) {
                arr[index + 1] = arr[index];
                index--;
            }
            arr[index + 1] = insertVal;
        }

        return arr;
    }
}
