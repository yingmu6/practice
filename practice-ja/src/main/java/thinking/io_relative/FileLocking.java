package thinking.io_relative;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

/**
 * @author orange
 * @date 2024/6/7
 */
public class FileLocking { //@TkY-Doing

    /**
     * 知识点（18.10.7）：文件加锁
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        FileOutputStream fos = new FileOutputStream("file3.txt");
        FileLock fl = fos.getChannel().tryLock();
        if (fl != null) {
            System.out.println("Locked File");
            TimeUnit.MILLISECONDS.sleep(100);
            fl.release();
            System.out.println("Released Lock");
        }

        fos.close();

        /**
         * 输出结果：
         * Locked File
         * Released Lock
         *
         * 结果分析：
         * 1）产生对应的加锁文件file.txt，然后对文件尝试加锁，最后释放锁并关闭输出流。
         *
         * 问题点答疑：
         * 1）此处的加锁文件，是在哪里被创建的？
         *
         * 2）FileLock与Lock有和不同？锁的类型都有哪些？
         */
    }
}
