package interview.offer_come.algorithm;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class ShellSort {

    /**
     * 希尔排序：
     *
     * 测试用例：
     */
    public static int[] sort(int[] arr) {
        int dk = arr.length / 3 + 1;
        while (dk != 1) {
            insertSort(arr, dk);
            dk = dk / 3 + 1;
        }

        if (dk == 1) {
            insertSort(arr, dk);
        }
        return arr;
    }

    public static void insertSort(int[] a, int dk) {
        for (int i = dk; i < a.length; i++) {
            if (a[i] < a[i-dk]) {
                int j;
                int x = a[i];
                a[i] = a[i - dk];
                for (j = i - dk; j > 0 && x < a[j]; j = j - dk) {
                    a[j + dk] = a[j];
                }
                a[j + dk] = x;
            }
        }
    }
}
