package relative.basic.io;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

/**
 * @author : chensy
 * Date : 2020/7/28 下午12:49
 */
public class ReaderTest {
    public static void main(String[] args) throws Exception{
        File file = new File("/Users/chenshengyong/selfPro/dubbo-util/basic-util/src/main/java/relative/basic/io/test.txt");
        System.out.println(file.exists());
//        Reader reader = new FileReader(file);

        /**
         * 问题集： todo 0728
         * 1）文件路径怎么简写，怎么使用相对路径
         */
    }
}

