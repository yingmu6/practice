package thinking.string_relative;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * @author orange
 * @date 2024/6/4
 */
public class Finding {

    /**
     * 知识点（13.6.3）：正则表达式查找
     */
    public static void main(String[] args) {
        Matcher m = Pattern.compile("\\w+")
                .matcher("Evening is full of the linnet's wings");
        while (m.find()) {
            printnb(m.group() + " ");
        }
        print();
        int i = 0;
        while (m.find()) {
            printnb(m.group() + " ");
            i++;
        }
    }
}
