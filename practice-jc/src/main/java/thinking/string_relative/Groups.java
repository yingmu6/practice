package thinking.string_relative;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * @author orange
 * @date 2024/6/4
 */
public class Groups {

    /**
     * 知识点（13.6.3）：组
     */
    static public final String POEM =
            "Twas brillig，and the slithy toves\n" +
            "Did gyre and gimble in the wabe.\n" +
            "All mimsy were the borogoves, \n" +
            "And the mome raths outgrade. \n\n" +
            "Beware the Jubberwock，my son，\n" +
            "The jaws that bite，the claws that catch. \n" +
            "Beware the Jubjub bird, and shun\n" +
            "The frumious Bandersnatch. ";

    public static void main(String[] args) {
        Matcher m =
                Pattern.compile("(?m)(\\S+)\\s+((\\S+)\\s+(\\S+))$").matcher(POEM);
        while (m.find()) {
            for (int j = 0; j <= m.groupCount(); j++) {
                printnb("[" + m.group(j) + "]");
            }
            print();
        }
    }
}
