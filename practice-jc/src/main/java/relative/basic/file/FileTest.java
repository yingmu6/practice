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
    public static void main(String[] args) throws IOException {
        File file = new File("a.txt");
        System.out.println(file.exists());


        File file2 = new File("/Users/chenshengyong/selfPro/practice/practice-jc/src/main/java/relative/basic/file/a.txt");
        System.out.println(file2.exists());

        // 读取文件内容
        InputStream inputStream = new FileInputStream(file2);
        System.out.println(new String(inputStream.readAllBytes()));
    }
}
