package thinking.generic_type;

import net.mindview.util.Generator;
import thinking.generic_type.coffee.Coffee;
import thinking.generic_type.coffee.CoffeeGenerator;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author chensy
 * @date 2024/5/18
 */
public class Generators {

    /**
     * 知识点（15.4.3）：用于Generator的泛型方法
     */

    public static <T>Collection fill(Collection<T> coll, Generator<T> gen, int n) {
        for (int i = 0; i < n; i++) {
            coll.add(gen.next());
        }
        return coll;
    }

    public static void main(String[] args) {
        Collection<Coffee> coffee = fill(new ArrayList<>(), new CoffeeGenerator(), 4);
        for (Coffee c : coffee) {
            System.out.println(c);
        }

        Collection<Integer> fnumbers = fill(new ArrayList<>(), new Fibonacci(), 12);
        for (int i : fnumbers) {
            System.out.println(i + "，");
        }
    }
}
