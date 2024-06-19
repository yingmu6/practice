package thinking.io_relative;

import java.io.*;

/**
 * @author orange
 * @date 2024/6/7
 */
public class Redirecting {

    /**
     * 知识点（18.8.3）：标准I/O重定向
     */
    public static void main(String[] args) throws IOException {
        PrintStream console = System.out;
        BufferedInputStream in = new BufferedInputStream(
                new FileInputStream("Redirecting.java")
        );
        PrintStream out = new PrintStream(
                new BufferedOutputStream(
                        new FileOutputStream("test.out")
                )
        );
        System.setIn(in);
        System.setOut(out);
        System.setErr(out);
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );
        String s;
        while ((s = br.readLine()) != null) {
            System.out.println(s);
        }
        out.close();
        System.setOut(console);
    }
}
