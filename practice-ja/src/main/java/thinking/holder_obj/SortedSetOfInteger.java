package thinking.holder_obj;

import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author orange
 * @date 2024/6/5
 */
public class SortedSetOfInteger {

    /**
     * 知识点（11.9）：Set
     */
    public static void main(String[] args) {
        Random rand = new Random(47);
        SortedSet<Integer> intSet = new TreeSet<>();
        for (int i = 0; i < 10000; i++) {
            intSet.add(rand.nextInt(30));
        }
        System.out.println(intSet);
    }
}
