package thinking.string_relative;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/4
 */
public class TestRegularExpression {

    /**
     * 知识点（13.6.3）：量词
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            print("Usage:\njava TestRegularExpress " +
                    "characterSequence regularExpresion+");
            System.exit(0);
        }

        print("Input：\"" + args[0] + "\"");
        for (String arg : args) {
            print("Regular expression：\"" + arg + "\"");
            Pattern p = Pattern.compile(arg);
            Matcher m = p.matcher(args[0]);
            while (m.find()) {
                print("Match \"" + m.group() + "\" at positions " +
                        m.start() + "-" + (m.end() - 1));
            }
        }
    }
}
