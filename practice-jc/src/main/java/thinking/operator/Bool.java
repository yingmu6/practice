package thinking.operator;

import java.util.Random;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/8
 */
public class Bool {

    /**
     * 知识点（3.8）：逻辑操作符
     */
    public static void main(String[] args) {
        Random rand = new Random(47);
        int i = rand.nextInt(100);
        int j = rand.nextInt(100);
        print("i = " + i);
        print("j = " + j);
        print("i > j is " + (i > j));
        print("i < j is " + (i < j));
        print("i >= j is " + (i >= j));
        print("i <= j is " + (i <= j));
        print("i == j is " + (i == j));
        print("i != j is " + (i != j));
        print("(i < 10) && (j < 10) is " + ((i < 10) && (j < 10)));
        print("(i < 10) || (j < 10) is " + ((i < 10) && (j < 10)));
    }
}
