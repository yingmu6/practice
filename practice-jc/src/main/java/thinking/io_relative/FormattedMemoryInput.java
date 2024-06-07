package thinking.io_relative;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

/**
 * @author orange
 * @date 2024/6/6
 */
public class FormattedMemoryInput {

    /**
     * 知识点（18.6.3）：格式化的内存输入
     */
    public static void main(String[] args) throws IOException {
        try {
            DataInputStream in = new DataInputStream(
                    new ByteArrayInputStream(
                            BufferedInputFile.read("FormattedMemoryInput.java").getBytes())
            );
            while (true) {
                System.out.println((char) in.readByte());
            }
        } catch (EOFException e) {
            System.err.println("End of stream");
        }
    }
}
