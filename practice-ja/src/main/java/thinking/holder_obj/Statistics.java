package thinking.holder_obj;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author orange
 * @date 2024/6/5
 */
public class Statistics {

    /**
     * 知识点（11.10）：Map
     */

    public static void main(String[] args) {
        Random rand = new Random(47);
        Map<Integer, Integer> m =
                new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            int r = rand.nextInt(20);
            Integer freq = m.get(r);
            m.put(r, freq == null ? 1 : freq + 1);
        }
        System.out.println(m);
    }
}
