package thinking.string_relative;

import java.util.ArrayList;
import java.util.List;

/**
 * @author orange
 * @date 2024/6/4
 */
public class InfiniteRecursion {
    public String toString() {
        return " InfiniteRecursion address：" + this + "\n";
    }

    public static void main(String[] args) {
        List<InfiniteRecursion> v = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            v.add(new InfiniteRecursion());
        }
        System.out.println(v);
    }
}
