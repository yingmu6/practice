package relative.basic.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author chensy
 * @date 2022/5/26
 */
public class FileTest { //flag
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
         * 2）path="/tmp/cc.txt"，文件生成到 "D:\tmp\cc.txt" (绝对路径)
         * 3）使用ClassLoader获取当前位置
         */
//        File file = new File("/tmp/cc.txt");
//        System.out.println("文件路径：" + file.getAbsolutePath());
//        if (!file.exists()) {
//           file.createNewFile();
//        }

        URL url = Thread.currentThread().getContextClassLoader().getResource("");
        System.out.println("路径1=" + url.getPath()); //输出的路径为"/D:/self_project/practice/practice-jc/target/classes/" (字节码对应的路径)

        // 相对路径（相对于项目路径的）
        System.out.println("路径2=" + new File("").getAbsolutePath()); //输出的路径为：D:\self_project\practice（项目对应的位置）

        // 相对路径
        System.out.println("路径3=" + new File("tmp/cc.txt").getAbsolutePath()); //输出的路径为：D:\self_project\practice\tmp\cc.txt

        // 当前类文件所在的包的绝对路径为 “D:\self_project\practice\practice-jc\src\main\java\relative\basic\file” （todo @csy 此处路径还得继续探索）
        // （从目前来看，没有找到直接获取当前文件位置的方法，按如下方式拼接即可----但是和idea右键获取类路径的绝对经也对不上，idea右键获取路径为：D:\self_project\practice\practice-jc\src\main\java\relative\basic\file\FileTest.java）
        Class class1 = FileTest.class;
        String javaPath = new File("").getAbsolutePath()+"\\src\\" + class1.getPackage().getName().replace(".", "\\");
        System.out.println("该Java文件所在文件夹路径为："+javaPath);
        String className = new File("").getAbsolutePath()+"\\src\\" + class1.getName().replace(".", "\\") + ".java";
        System.out.println("该Java文件路径为："+className);

        /**
         * FileTest类所处位置
         * D:\self_project\practice\practice-jc\src\main\java\relative\basic\file
         *
         */
        String testPath = new File("").getAbsolutePath() + "\\src\\" +  class1.getPackage().getName().replace(".", "\\") + "\\cc.txt";
        File testFile = new File(testPath);
        System.out.println("路径4：" + testPath);
//        if (!testFile.exists()) {
//            testFile.createNewFile(); // 此处会报错，因为值为“D:\self_project\practice\src\relative\basic\file\cc.txt”, 没有显示模块"practice-jc" 以及"\src\main\java"
//        }
    }

    /**
     * 绝对路径：绝对路径就是你的主页上的文件或目录在硬盘上真正的路径，(URL和物理路径)
     * 相对路径：相对与某个基准目录的路径。包含Web的相对路径(HTML中的相对目录)
     */
}
