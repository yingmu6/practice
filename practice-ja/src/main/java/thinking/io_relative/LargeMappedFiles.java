package thinking.io_relative;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.RandomAccess;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * @author orange
 * @date 2024/6/7
 */
public class LargeMappedFiles {

    /**
     * 知识点（18.10.6）：内存映射文件
     */
    static int length = 0x8FFFFFF;

    public static void main(String[] args) throws IOException {
        MappedByteBuffer out =
                new RandomAccessFile("test.dat", "rw").getChannel()
                        .map(FileChannel.MapMode.READ_WRITE, 0, length);
        for (int i = 0; i < length; i++) {
            out.put((byte) 'x');
        }
        print("Finished writing");
        for (int i = length / 2; i < length / 2 + 6; i++) {
            printnb((char)out.get(i));
        }
    }
}
