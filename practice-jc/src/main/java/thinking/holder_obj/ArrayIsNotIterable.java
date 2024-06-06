package thinking.holder_obj;

import java.util.Arrays;

/**
 * @author orange
 * @date 2024/6/5
 */
public class ArrayIsNotIterable {

    /**
     * 知识点（11.13）：
     */
    static <T> void test(Iterable<T> ib) {
        for (T t : ib) {
            System.out.println(t + " ");
        }
    }

    public static void main(String[] args) {
        test(Arrays.asList(1, 2, 3));
        String[] strings = {"A", "B", "C"};
        test(Arrays.asList(strings));
    }
}
