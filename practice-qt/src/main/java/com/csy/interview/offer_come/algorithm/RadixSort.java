package com.csy.interview.offer_come.algorithm;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class RadixSort {

    /**
     * 基数排序：
     */
    public static int[] sort(int[] array, int maxDigit) {
        double max = Math.pow(10, maxDigit);
        int n = 1;
        int k = 0;
        int length = array.length;
        int[][] bucket = new int[10][length];
        int[] order = new int[length];
        while (n < max) {
            for (int num : array) {
                int digit = (num / n) % 10;
                bucket[digit][order[digit]] = num;
                order[digit]++;
            }

            for (int i = 0; i < length; i++) {
                if (order[i] != 0) {
                    for (int j = 0; j < order[i]; j++) {
                        array[k] = bucket[i][j];
                        k++;
                    }
                }
                order[i] = 0;
            }

            n *= 10;
            k = 0;
        }
        return array;
    }
}
