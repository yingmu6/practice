package thinking.concurrent;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class ResponsiveUI extends Thread {

    static class UnresponsiveUI {
        private volatile double d = 1;
        public UnresponsiveUI() throws Exception {
            while (d > 0)
                d = d + (Math.PI + Math.E) / d;
            System.in.read();
        }
    }

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

    public static void main(String[] args) throws Exception { //创建有响应的界面
        // new UnresponsiveUI();
        new ResponsiveUI();
        System.in.read();
        System.out.println(d);
    }
}
