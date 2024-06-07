package thinking.io_relative;

import java.io.*;

/**
 * @author orange
 * @date 2024/6/7
 */
public class BasicFileOutput {

    /**
     * 知识点（18.6.4）：基本的文件输出
     */
    static String file = "BasicFileOutput.out";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(
                new StringReader(
                        BufferedInputFile.read("BasicFileOutput.java"))
        );

        PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter(file)));
        int lineCount = 1;
        String s;
        while ((s = in.readLine()) != null) {
            out.println(lineCount++ + "：" + s);
        }
        out.close();
        System.out.println(BufferedInputFile.read(file));
    }
}
