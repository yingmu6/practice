package thinking.string_relative;

import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author orange
 * @date 2024/6/5
 */
public class ReplacingStringTokenizer {

    /**
     * 知识点（13.8）：StringTokenizer
     */
    public static void main(String[] args) {
        String input = "But I'm not dead yet! I feel happy!";
        StringTokenizer stoke = new StringTokenizer(input);
        while (stoke.hasMoreElements()) {
            System.out.println(stoke.nextToken() + " ");
        }
        System.out.println();
        System.out.println(Arrays.toString(input.split(" ")));
    }
}
