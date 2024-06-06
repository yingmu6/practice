package thinking.holder_obj;

import java.util.*;

/**
 * @author orange
 * @date 2024/6/5
 */
public class ModifyArrayAsList {

    /**
     * 知识点（11.13）
     */
    public static void main(String[] args) {
        Random rand = new Random(47);
        Integer[] ia = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        List<Integer> list1 =
                new ArrayList<>(Arrays.asList(ia));
        System.out.println("Before shuffling：" + list1);
        Collections.shuffle(list1, rand);
        System.out.println("After shuffling：" + list1);
        System.out.println("array：" + Arrays.toString(ia));

        List<Integer> list2 = Arrays.asList(ia);
        System.out.println("Before shuffing: " + list2);
        Collections.shuffle(list2, rand);
        System.out.println("After shuffing：" + list2);
        System.out.println("array：" + Arrays.toString(ia));
    }
}
