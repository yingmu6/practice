package thinking.holder_obj;

import java.util.*;

/**
 * @author orange
 * @date 2024/6/5
 */
public class AddingGroups {

    /**
     * 知识点（11.3）：添加一组元素
     */
    public static void main(String[] args) {
        Collection<Integer> collection =
                new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Integer[] moreInts = {6, 7, 8, 9, 10 };
        collection.addAll(Arrays.asList(moreInts));

        Collections.addAll(collection, 11, 12, 13, 14, 15);
        Collections.addAll(collection, moreInts);
        List<Integer> list = Arrays.asList(16, 17, 18, 19, 20);
        list.set(1, 99);
    }
}
