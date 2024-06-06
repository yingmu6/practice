package thinking.holder_obj;

import java.util.ArrayList;

/**
 * @author orange
 * @date 2024/6/5
 */
public class AppleAndOrangesWithoutGenerics {

    /**
     * 知识点（11.1）：泛型和类型安全的容器
     */
    static class Apple {
        private static long counter;
        private final long id = counter++;

        public long id() { return id; }
    }

    static class Orange {}

    public static void main(String[] args) {
        ArrayList apples = new ArrayList();
        for (int i = 0; i < 3; i++) {
            apples.add(new Apple());
        }

        apples.add(new Orange());
        for (int i = 0; i < apples.size(); i++) {
            ((Apple)apples.get(i)).id();
        }
    }
}
