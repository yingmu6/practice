package interview.written_exam.io.socket_ext;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author chensy
 * @date 2023/8/20
 */
public class SocketClient {
    public static void main(String[] args) {
        BufferedReader br = null;
        PrintWriter pw = null;
        try {
            Socket socket = new Socket("localhost", 2000);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream(), true);
            pw.print("Hello");
            String s = null;
            while (true) {
                s = br.readLine();
                if (s != null) {
                    break;
                }
            }
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                pw.close();
            } catch (Exception e) {

            }
        }
    }

    /**
     * 输出结果：
     *
     * 结果分析：
     *
     * 疑问点：todo @csy-08/20
     * 1）为啥客户端启动什么都没有打印出结果
     */
}
