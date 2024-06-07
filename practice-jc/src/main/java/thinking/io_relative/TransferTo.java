package thinking.io_relative;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author orange
 * @date 2024/6/7
 */
public class TransferTo {

    /**
     * 知识点（18.10）：数据转换
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.print("arguments：sourcefile destfile");
            System.exit(1);
        }

        FileChannel
                in = new FileInputStream(args[0]).getChannel(),
                out = new FileOutputStream(args[1]).getChannel();
        in.transferFrom(in, 0, in.size());
    }
}
