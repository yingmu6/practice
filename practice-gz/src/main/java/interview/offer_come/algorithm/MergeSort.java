package interview.offer_come.algorithm;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class MergeSort {

    /**
     * 归并排序：
     *
     * 测试用例：
     * 原始数据：[4,1,3,9,6,8]
     * 分解：
     * 第1次分解：[4,1,3]、[9,6,8]
     * 第2次分解：[4,1]、[3]、[9,6]、[8]
     *
     * 归并：
     * 第1次排序归并：[1,3,4]、[6,8,9]
     * 第2次排序归并：[1,3,4,6,8,9]
     */
    public static int[] mergeSort(int[] data) {
        sort(data, 0, data.length - 1);
        return data;
    }

    public static void sort(int[] data, int left, int right) {
        if (left >= right) {
            return;
        }

        int center = (left + right) / 2;
        sort(data, left, center);
        sort(data, center + 1, right);
        merge(data, left, center, right);
    }

    public static void merge(int[] data, int left, int center, int right) {
        int[] tmpArr = new int[data.length];
        int mid = center + 1;
        int third = left;
        int tmp = left;
        while (left <= center && mid <= right) {
            if (data[left] <= data[mid]) {
                tmpArr[third++] = data[left++];
            } else {
                tmpArr[third++] = data[mid++];
            }
        }

        while (mid <= right) {
            tmpArr[third++] = data[mid++];
        }

        while (left <= center) {
            tmpArr[third++] = data[left++];
        }

        while (tmp <= right) {
            data[tmp] = tmpArr[tmp++];
        }
    }
}
