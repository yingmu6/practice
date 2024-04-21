package thinking.collection_deep;

import net.mindview.util.CollectionData;
import net.mindview.util.Generator;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author chensy
 * @date 2024/4/21
 */
public class CollectionDataTest {

    /**
     * 知识点（17.2）：初始化LinkedHashSet
     */

    static class Government implements Generator<String> {
        String[] foundation = ("strange women lying in ponds " +
                "distributing swords is no basis for a system of " +
                "government").split(" ");
        private int index;
        public String next() {
            return foundation[index++];
        }
    }

    public static void main(String[] args) {
        Set<String> set = new LinkedHashSet<>(new CollectionData<>(new Government(), 15));
        set.addAll(CollectionData.list(new Government(), 15));
        System.out.println(set);
    }
}
