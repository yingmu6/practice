package thinking.io_relative;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

/**
 * @author orange
 * @date 2024/6/7
 */
public class FileOutputShortcut { //@TkY-Doing

    /**
     * 知识点（18.6.4）：文件文本输出的快捷方式
     */
    static String file = "FileOutputShortcut.out";

    public static void main(String[] args) throws IOException {
        // String filePath = "FileOutputShortcut.java";
        String filePath = "/Users/shengyong.chen/self_remote/practice/practice-ja/src/main/java/thinking/io_relative/FileOutputShortcut.java";
        BufferedReader in = new BufferedReader(
                new StringReader(
                        BufferedInputFile.read(filePath)
                )
        );

        PrintWriter out = new PrintWriter(file);
        int lineCount = 1;
        String s;
        while ((s = in.readLine()) != null) {
            out.println(lineCount++ + "：" + s);
        }
        out.close();
        System.out.println(BufferedInputFile.read(file));

        /**
         * 输出结果：
         *
         * 结果分析：
         *
         * 问题点答疑：
         * 1）报出FileOutputShortcut.java (No such file or directory) ，为啥会找不到文件，是不是路径有问题？
         *    解答：使用了FileOutputShortcut.java绝对路径，就可以找到文件了（为啥相对路径找不到？）
         */
    }
}
