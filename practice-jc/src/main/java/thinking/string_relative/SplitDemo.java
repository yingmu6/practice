package thinking.string_relative;

import java.util.Arrays;
import java.util.regex.Pattern;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/4
 */
public class SplitDemo {

    /**
     * 知识点（13.6.5）：split()
     */
    public static void main(String[] args) {
        String input =
                "This!!unusual use!!of exclamation!!points";
        print(Arrays.toString(
                Pattern.compile("!!").split(input)));
        print(Arrays.toString(
                Pattern.compile("!!").split(input, 3)));
    }
}
