package thinking.generic_type;

import net.mindview.util.FiveTuple;
import net.mindview.util.FourTuple;
import net.mindview.util.ThreeTuple;
import net.mindview.util.TwoTuple;

import static net.mindview.util.Tuple.tuple;

/**
 * @author chensy
 * @date 2024/5/18
 */
public class TupleTest2 {

    /**
     * 知识点（15.4.5）：简化元组的使用
     */
    static TwoTuple<String, Integer> f() {
        return tuple("hi", 47);
    }

    static TwoTuple f2() {
        return tuple("hi", 47);
    }

    static ThreeTuple<TupleTest.Amphibian, String, Integer> g() {
        return tuple(new TupleTest.Amphibian(), "hi", 47);
    }

    static FourTuple<TupleTest.Vehicle, TupleTest.Amphibian, String, Integer> h() {
        return tuple(new TupleTest.Vehicle(), new TupleTest.Amphibian(), "hi", 47);
    }

    static FiveTuple<TupleTest.Vehicle, TupleTest.Amphibian, String, Integer, Double> k() {
        return tuple(new TupleTest.Vehicle(), new TupleTest.Amphibian(), "hi", 47, 11.1);
    }

    public static void main(String[] args) {
        TwoTuple<String, Integer> ttsi = f();
        System.out.println(ttsi);
        System.out.println(f2());
        System.out.println(g());
        System.out.println(h());
        System.out.println(k());
    }
}
