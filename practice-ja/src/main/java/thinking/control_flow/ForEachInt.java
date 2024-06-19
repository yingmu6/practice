package thinking.control_flow;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;
import static net.mindview.util.Range.range;

/**
 * @author orange
 * @date 2024/6/3
 */
public class ForEachInt {

    /**
     * 知识点（4.4）：Foreach语法
     */
    public static void main(String[] args) {
        for (int i : range(10)) {
            printnb(i + " ");
        }
        print();

        for (int i : range(5, 10)) {
            printnb(i + " ");
        }
        print();

        for (int i : range(5, 20, 3)) {
            printnb(i + " ");
        }
        print();
    }
}
