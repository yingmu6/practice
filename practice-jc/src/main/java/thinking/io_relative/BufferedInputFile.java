package thinking.io_relative;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author orange
 * @date 2024/6/6
 */
public class BufferedInputFile {

    /**
     * 缓冲输入文件
     */
    public static String read(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = in.readLine()) != null) {
            sb.append(s + "\n");
        }
        in.close();
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(read("BufferedInputFile.java"));
    }
}
