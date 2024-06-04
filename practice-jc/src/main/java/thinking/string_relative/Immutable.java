package thinking.string_relative;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/4
 */
public class Immutable {

    /**
     * 知识点（13.1）：不可变String
     */
    public static String upcase(String s) {
        return s.toUpperCase();
    }

    public static void main(String[] args) {
        String q = "howdy";
        print(q);
        String qq = upcase(q);
        print(qq);
        print(q);
    }
}
