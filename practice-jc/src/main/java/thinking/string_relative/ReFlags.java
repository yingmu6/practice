package thinking.string_relative;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author orange
 * @date 2024/6/4
 */
public class ReFlags {

    /**
     * 知识点（13.6.4）：Pattern标记
     */
    public static void main(String[] args) {
        Pattern p = Pattern.compile("^java", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        Matcher m = p.matcher(
          "java has regex\nJava has regex\n" +
         "JAVA has pretty good regular expressions\n" +
         "Regular expressions are in Java");

        while (m.find()) {
            System.out.println(m.group());
        }
    }
}
