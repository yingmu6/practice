package thinking.io_relative;

import net.mindview.util.Directory;
import net.mindview.util.PPrint;

import java.io.File;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/6
 */
public class DirectoryDemo {

    /**
     * 知识点（18.1.2）：目录使用工具
     */
    public static void main(String[] args) {
        PPrint.pprint(Directory.walk(".").dirs);
        for (File file : Directory.local(".", "T.*")) {
            print(file);
        }
        print("----------------");
        for (File file : Directory.walk(".", "T.*\\.java")) {
            print(file);
        }
        print("================");
        for (File file : Directory.walk(".", ".*[Zz].*\\.class")) {
            print(file);
        }
    }
}
