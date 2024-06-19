package thinking.holder_obj;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author orange
 * @date 2024/6/5
 */
public class SetOfInteger {

    /**
     * 知识点（11.9）：Set
     */
    public static void main(String[] args) {
        Random rand = new Random(47);
        Set<Integer> intSet = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            intSet.add(rand.nextInt(30));
        }
        System.out.println(intSet);
    }
}
