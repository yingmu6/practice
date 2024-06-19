package thinking.operator;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/8
 */
public class URShift {

    /**
     * 知识点（3.11）：移位操作符
     */
    public static void main(String[] args) {
        int i = -1;
        print(Integer.toBinaryString(i));
        i >>>= 10;
        print(Integer.toBinaryString(i));
        long l = -1;
        print(Long.toBinaryString(l));
        l >>>= 10;
        print(Long.toBinaryString(l));
        short s = -1;
        print(Integer.toBinaryString(s));
        s >>>= 10;
        print(Integer.toBinaryString(s));
        byte b = -1;
        print(Integer.toBinaryString(b));
        b >>>= 10;
        print(Integer.toBinaryString(b));
        b = -1;
        print(Integer.toBinaryString(b));
        print(Integer.toBinaryString(b >>> 10));
    }
}
