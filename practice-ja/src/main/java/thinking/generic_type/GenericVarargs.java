package thinking.generic_type;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chensy
 * @date 2024/5/18
 */
public class GenericVarargs {

    /**
     * 知识点（15.4.2）：可变参数与泛型方法
     */

    public static <T> List<T> makeList(T... args) {
        List<T> result = new ArrayList<>();
        for (T item : args)
            result.add(item);
        return result;
    }

    public static void main(String[] args) {
        List<String> ls = makeList("A");
        System.out.println(ls);
        ls = makeList("A", "B", "C");
        System.out.println(ls);
        ls = makeList("ABCDEFFHIJKLMNOPQRSTUVWXYZ".split(""));
        System.out.println(ls);
    }
}
