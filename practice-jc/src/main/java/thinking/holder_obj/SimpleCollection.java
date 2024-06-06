package thinking.holder_obj;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author orange
 * @date 2024/6/5
 */
public class SimpleCollection {

    /**
     * 知识点（11.2）：
     */
    public static void main(String[] args) {
        Collection<Integer> c = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            c.add(i);
        }
        for (Integer i : c) {
            System.out.println(i + ", ");
        }
    }
}
