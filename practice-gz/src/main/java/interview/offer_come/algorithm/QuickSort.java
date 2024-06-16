package interview.offer_come.algorithm;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class QuickSort {

    /**
     * 快速排序：
     *
     * 测试用例：
     * 初始数组：[6,9,5,7,8]
     * 第1次交换：[5,9,6,7,8]
     * 第2次交换：[5,6,9,7,8]
     */
    public static int[] quickSort(int[] arr, int low, int high) {
        int start = low;
        int end = high;
        int key = arr[low];
        while (end > start) {
            while (end > start && arr[end] >= key) {
                end--;
            }

            if (arr[end] <= key) {
                int temp = arr[end];
                arr[end] = arr[start];
                arr[start] = temp;
            }

            while (end > start && arr[start] <= key) {
                start++;
            }

            if (arr[start] >= key) {
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
            }
        }

        if (start > low) {
            quickSort(arr, low, start - 1);
        }

        if (end < high) {
            quickSort(arr, end + 1, high);
        }
        return arr;
    }
}
