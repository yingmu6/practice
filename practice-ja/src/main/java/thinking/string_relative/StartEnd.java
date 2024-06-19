package thinking.string_relative;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/4
 */
public class StartEnd {

    /**
     * 知识点（13.6.4）：start()与end()
     */
    public static String input =
            "As long as there is injustice，whenever a\n" +
            "Targathian baby cries out, wherever a distrss\n" +
            "signal sounds among the stars ... We'll be there. \n" +
            "This fine ship, and this fine crew ...\n" +
            "Never give up! Never surrender!";

    private static class Display {
        private boolean regexPrinted = false;
        private String regex;
        Display(String regex) {
            this.regex = regex;
        }
        void display(String message) {
            if (!regexPrinted) {
                print(regex);
                regexPrinted = true;
            }
            print(message);
        }
    }

    static void exmaing(String s, String regex) {
        Display d = new Display(regex);
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);
        while (m.find()) {
            d.display("find() '" + m.group() +
                    "' start = " + m.start() + " end = " + m.end());
        }

        if (m.lookingAt()) {
            d.display("lookingAt() start = "
                            + m.start() + " end = " + m.end());
        }

        if (m.matches()) {
            d.display("matches() start = "
            + m.start() + " end = " + m.end());
        }
    }

    public static void main(String[] args) {
        for (String in : input.split("\n")) {
            print("input : " + in);
            for (String regex : new String[] {
                    "\\w*ere\\w", "\\w*ever", "T\\w+", "Never.*?!"}) {
                exmaing(in, regex);
            }
        }
    }
}
