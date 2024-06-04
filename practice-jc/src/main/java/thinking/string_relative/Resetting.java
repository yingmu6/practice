package thinking.string_relative;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author orange
 * @date 2024/6/4
 */
public class Resetting {

    /**
     * 知识点（13.6.7）：reset()
     */
    public static void main(String[] args) {
        Matcher m = Pattern.compile("[frb][aiu][gx]]")
                .matcher("fix the rug with bags");
        while (m.find()) {
            System.out.println(m.group() + " ");
        }
        System.out.println();
        m.reset("fix the fig with rags");
        while (m.find()) {
            System.out.println(m.group() + " ");
        }
    }
}
