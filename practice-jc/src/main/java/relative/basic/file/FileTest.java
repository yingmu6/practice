package relative.basic.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author chensy
 * @date 2022/5/26
 */
public class FileTest {
    public static void main(String[] args) throws Exception {
        createFileByRelativePath();
    }

    public static void basicUse() throws Exception {
        File file = new File("a.txt");
        System.out.println(file.exists());


        File file2 = new File("/Users/chenshengyong/selfPro/practice/practice-jc/src/main/java/relative/basic/file/a.txt");
        System.out.println(file2.exists());

        // 读取文件内容
        InputStream inputStream = new FileInputStream(file2);
//        System.out.println(new String(inputStream.readAllBytes()));

    }

    /**
     * 通过相对路径创建文件
     */
    public static void createFileByRelativePath() throws Exception {
        /**
         * 相对路径说明：
         * 1）path="cc.txt"，文件生成到practice项目下
         * 2）path="/tmp/cc.txt"，文件生成到 "D:\tmp\cc.txt"
         * 3）使用ClassLoader获取位置
         */
        File file = new File("/tmp/cc.txt");
        System.out.println("文件路径：" + file.getAbsolutePath());
        if (!file.exists()) {
           file.createNewFile();
        }
    }

    /**
     * 绝对路径：绝对路径就是你的主页上的文件或目录在硬盘上真正的路径，(URL和物理路径)
     * 相对路径：相对与某个基准目录的路径。包含Web的相对路径(HTML中的相对目录)
     */
}
