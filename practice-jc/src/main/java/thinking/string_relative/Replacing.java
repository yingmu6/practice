package thinking.string_relative;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/4
 */
public class Replacing {

    /**
     * 知识点（13.6.1）：正则表达式
     */
    static String s = Splitting.knights;

    public static void main(String[] args) {
        print(s.replaceFirst("f\\w", "located"));
        print(s.replaceAll("shrubbery|tree|herring", "banana"));
    }
}
