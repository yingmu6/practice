package thinking.generic_type;

import java.util.ArrayList;

/**
 * @author chensy
 * @date 2024/5/18
 */
public class ErasedTypeEquivalence {

    /**
     * 知识点（15.7）：擦除的神秘之处
     */
    public static void main(String[] args) {
        Class c1 = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();
        System.out.println(c1 == c2);
    }
}
