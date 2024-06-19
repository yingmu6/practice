package thinking.io_relative;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * @author orange
 * @date 2024/6/7
 */
public class LockingMappedFiles {

    /**
     * 知识点（18.10.7）：对映射文件的部分加锁
     */
    static final int LENGTH = 0x8FFFFFF;
    static FileChannel fc;

    public static void main(String[] args) throws IOException {
        fc =
                new RandomAccessFile("test.dat", "rw").getChannel();
        MappedByteBuffer out =
                fc.map(FileChannel.MapMode.READ_WRITE, 0, LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            out.put((byte) 'x');
        }


    }

    private static class LockAndModify extends Thread {
        private ByteBuffer buff;
        private int start, end;
        LockAndModify(ByteBuffer mbb, int start, int end) {
            this.start = start;
            this.end = end;
            mbb.limit(end);
            mbb.position(start);
            buff = mbb.slice();
            start();
        }

        public void run() {
            try {
                FileLock fl = fc.lock(start, end, false);
                System.out.println("Locked：" + start + " to " + end);
                while (buff.position() < buff.limit() - 1) {
                    buff.put((byte) (buff.get() + 1));
                }
                fl.release();
                System.out.println("Release：" + start + " to " + end);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
