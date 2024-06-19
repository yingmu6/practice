package thinking.string_relative;

import net.mindview.util.TextFile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author orange
 * @date 2024/6/4
 */
public class JGrep {

    /**
     * 知识点（13.6.8）：正则表达式与Java I/O
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage：java JGrep file regex");
            System.exit(0);
        }

        Pattern p = Pattern.compile(args[1]);
        int index = 0;
        Matcher m = p.matcher("");
        for (String line : new TextFile(args[0])) {
            m.reset(line);
            while (m.find()) {
                System.out.println(index++ + "：" +
                        m.group() + "：" + m.start());
            }
        }
    }
}
