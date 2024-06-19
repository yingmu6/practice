package thinking.generic_type;

import net.mindview.util.FiveTuple;
import net.mindview.util.FourTuple;
import net.mindview.util.ThreeTuple;
import net.mindview.util.TwoTuple;

/**
 * @author chensy
 * @date 2024/5/17
 */
public class TupleTest {

    /**
     * 知识点（15.2.1）：一个元组类库
     */

    static class Amphibian {}
    static class Vehicle {}

    static TwoTuple<String, Integer> f() {
        return new TwoTuple<>("hi", 47);
    }

    static ThreeTuple<Amphibian, String, Integer> g() {
        return new ThreeTuple<>(new Amphibian(), "hi", 47);
    }

    static FourTuple<Vehicle, Amphibian, String, Integer> h() {
        return new FourTuple<>(new Vehicle(), new Amphibian(), "hi", 47);
    }

    static FiveTuple<Vehicle, Amphibian, String, Integer, Double> k() {
        return new FiveTuple<>(new Vehicle(), new Amphibian(), "hi", 47, 11.1);
    }

    public static void main(String[] args) {
        TwoTuple<String, Integer> ttsi = f();
        System.out.println(ttsi);
        // ttsi.first = "three"; //此处会报出不能赋值给final错误
        System.out.println(g());
        System.out.println(h());
        System.out.println(k());
    }
}
