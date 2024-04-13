package thinking.concurrent.case4;

import java.io.IOException;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class ResponsiveUI extends Thread {

    private static volatile double d = 1;

    public ResponsiveUI() {
        setDaemon(true);
        start();
    }

    public void run() {
        while (true) {
            d = d + (Math.PI + Math.E) / d;
        }
    }

    public static void main(String[] args) throws IOException { //创建有响应的界面
        new ResponsiveUI();
        System.in.read();
        System.out.println(d);
    }
}
