package com.csy.interview.offer_come.algorithm;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class BucketSort {

    /**
     * 桶排序：
     *
     * 测试用例：
     * 原始数据：[3,6,5,9,7,8]
     *
     */
    public static int[] sort(int[] arr) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }

        int bucketNum = (max - min) / arr.length + 1;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketNum);
        for (int i = 0; i < bucketNum; i++) {
            bucketArr.add(new ArrayList<>());
        }

        for (int i = 0; i < arr.length; i++) {
            int num = (arr[i] - min) / (arr.length);
            bucketArr.get(num).add(arr[i]);
        }

        for (int i = 0; i < bucketArr.size(); i++) {
            Collections.sort(bucketArr.get(i));
        }

        ArrayList<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < bucketArr.size(); i++) {
            resultList.addAll(bucketArr.get(i));
        }

        for (int i = 0; i < resultList.size(); i++) {
            arr[i] = resultList.get(i);
            return arr;
        }
        return arr;
    }
}
