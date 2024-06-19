package relative.jvm;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Stream.empty;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * 进程类测试
 * https://blog.csdn.net/zhoulupeng2012/article/details/49430455
 * <p>
 * Process类是一个抽象类（所有的方法均是抽象的），封装了一个进程（即一个执行程序）。
 * Process 类提供了执行从进程输入、执行输出到进程、等待进程完成、检查进程的退出状态以及销毁（杀掉）进程的方法。
 * ProcessBuilder.start() 和 Runtime.exec 方法创建一个本机进程，并返回 Process 子类的一个实例，
 * 该实例可用来控制进程并获取相关信息。
 *
 * @author chensy
 * @date 2019-05-30 14:17
 */
public class ProcessTest {

    /**
     * Process：进程测试
     * 1）The Process API provides a powerful way to execute operating system commands in Java.
     *    However, it has several options that can make it cumbersome（繁琐的） to work with
     *
     * 2）The ProcessBuilder class provides methods for creating and configuring operating system processes.
     *    Each ProcessBuilder instance allows us to manage a collection of process attributes.
     *
     * 参考链接：
     * 1）https://www.baeldung.com/java-lang-processbuilder-api  ProcessBuilder API使用
     * 2）https://www.baeldung.com/java-process-api Process API使用
     */

    /**
     * 读取控制命令的输出结果
     */
    public static void main(String[] args) throws Exception {
//        String javaHome = System.getProperty("java.home");
//        String cmd = javaHome + File.separator + "bin" + File.separator + "java" + " -version"; //获取环境变量信息，构建指令
        String cmd = "java -version";
        StringBuffer outStr = new StringBuffer();
        Process process = Runtime.getRuntime().exec(cmd);     //执行一个系统命令
        InputStream fis = process.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line;
        while ((line = br.readLine()) != null) { //按行读取
            outStr.append(line + ", ");
        }

        System.out.println("执行结果:" + outStr);

        /**
         * 输出结果：
         *
         * 结果分析：
         *
         * 问题点：todo @csy-08/17
         * 1）此处为啥没有输出结果，莫非是没有执行"java -version"吗？
         * 2）为啥构造出指令 "/Library/Java/JavaVirtualMachines/jdk1.8.0_171.jdk/Contents/Home/jre/bin/java -version"执行不了，但放入iterm终端是能执行的
         */
    }

    /**
     * 场景1：使用ProcessBuilder构建进程
     */
    @Test
    public void test_create_process_with_builder() throws IOException, InterruptedException {
        Process process = new ProcessBuilder("java", "-version").start(); //启动进程

        List<String> results = readOutput(process.getInputStream());

        assertThat("Results should not be empty", results, is(not(empty())));
        assertThat("Results should contain java version: ", results, hasItem(containsString("java version")));

        int exitCode = process.waitFor();
        assertEquals("No errors should be detected", 0, exitCode);
    }

    private List<String> readOutput(InputStream is) throws IOException {
        List<String> results = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(is)); //由底层流构造高级流
        String line;
        while ((line = br.readLine()) != null) { //依次按行读取
            results.add(line);
        }
        return results;
    }

    /**
     * 场景2：执行指定应用程序
     */
    @Test
    public void test_execute_application() throws IOException, InterruptedException {
        Runtime r = Runtime.getRuntime();
        Process p = r.exec("WeChat");
        p.waitFor(10, TimeUnit.SECONDS);
        p.destroy();
    }
}
