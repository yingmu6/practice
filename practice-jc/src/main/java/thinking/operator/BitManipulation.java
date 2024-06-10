package thinking.operator;

import java.util.Random;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/8
 */
public class BitManipulation {

    /**
     * 知识点（3.11）:移位操作符
     */
    public static void main(String[] args) {
        Random rand = new Random(47);
        int i = rand.nextInt();
        int j = rand.nextInt();
        printBinaryInt("-1", -1);
        printBinaryInt("+1", +1);
        int maxpos = 2147483647;
        printBinaryInt("maxpost", maxpos);
        int maxneg = -2147483648;
        printBinaryInt("maxneg", maxneg);
        printBinaryInt("i", i);

    }

    static void printBinaryInt(String s, int i) {
        print(s + "，int：" + i + "，binary：\n " +
                Integer.toBinaryString(i));
    }

    static void printBinaryLong(String s, long l) {
        print(s + "，long：" + l + "，binary：\n " +
                Long.toBinaryString(l));
    }
}
