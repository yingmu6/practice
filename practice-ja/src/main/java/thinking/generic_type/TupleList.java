package thinking.generic_type;

import net.mindview.util.FourTuple;

import java.util.ArrayList;

/**
 * @author chensy
 * @date 2024/5/18
 */
public class TupleList<A, B, C, D>
        extends ArrayList<FourTuple<A, B, C, D>> {

    /**
     * 知识点（15.6）：构建复杂模型
     */
    public static void main(String[] args) {
        TupleList<TupleTest.Vehicle, TupleTest.Amphibian, String, Integer> tl =
                new TupleList<>();
        tl.add(TupleTest.h());
        tl.add(TupleTest.h());
        for (FourTuple<TupleTest.Vehicle, TupleTest.Amphibian, String, Integer> i : tl) {
            System.out.println(i);
        }
    }
}
