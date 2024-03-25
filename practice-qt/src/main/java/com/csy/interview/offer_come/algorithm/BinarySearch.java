package com.csy.interview.offer_come.algorithm;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class BinarySearch {

    /**
     * 二分查找算法：
     *
     * 测试用例：
     * 在有序数组[3,4,6,20,40,45,51,62,70,99,110]查找key=20的数据，只需两次可命中
     */

    public static int sort(int []array, int a) {
        int low = 0;
        int high = array.length - 1;
        int mid;
        while (low <= high) {
            mid = (high - low) / 2 + low;
            if (array[mid] == a) {
                return mid;
            } else if (a > array[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }
}
