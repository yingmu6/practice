package thinking.io_relative;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * @author orange
 * @date 2024/6/6
 */
public class MemoryInput {

    /**
     * 知识点（18.6.2 ）：从内存输入
     */
    public static void main(String[] args) throws IOException {
        StringReader in =
                new StringReader(BufferedInputFile.read("MemoryInput.java"));
        int c;
        while ((c = in.read()) != -1) {
            System.out.print((char) c);
        }
    }
}
