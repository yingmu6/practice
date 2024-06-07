package thinking.io_relative;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

/**
 * @author orange
 * @date 2024/6/7
 */
public class FileOutputShortcut {

    /**
     * 知识点（18.6.4）：文件文本输出的快捷方式
     */
    static String file = "FileOutputShortcut.out";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(
                new StringReader(
                        BufferedInputFile.read("FileOutputShortcut.java")
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
    }
}
