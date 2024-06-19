package thinking.io_relative;

import java.io.*;

/**
 * @author orange
 * @date 2024/6/6
 */
public class TestEOF {

    /**
     * 知识点（18.6.3）：格式化的内存输入
     */
    public static void main(String[] args) throws IOException {
        DataInputStream in =
                new DataInputStream(new BufferedInputStream(
                        new FileInputStream("TestEOF.java")
                ));
        while (in.available() != 0) {
            System.out.print((char) in.readByte());
        }
    }
}
