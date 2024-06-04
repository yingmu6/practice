package thinking.string_relative;

import java.util.Arrays;

/**
 * @author orange
 * @date 2024/6/4
 */
public class Splitting {

    /**
     * 知识点（13.6）：正则表达式
     */
    public static String knights =
            "Then，when you have found the shrubbery，you must " +
                    "cut down the mightiest tree in the forest... " +
                    "with... a herring!";

    public static void split(String regex) {
        System.out.println(Arrays.toString(knights.split(regex)));
    }

    public static void main(String[] args) {
        split(" ");
        split("\\W+");
        split("n\\W+");
    }
}
