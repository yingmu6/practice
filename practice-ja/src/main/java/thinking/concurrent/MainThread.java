package thinking.concurrent;

import thinking.concurrent.LiftOff;

/**
 * @author chensy
 * @date 2024/4/13
 */
public class MainThread {
    public static void main(String[] args) { //实现Runnable接口
        LiftOff launch = new LiftOff();
        launch.run();
    }
}
