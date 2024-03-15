package com.csy.interview.offer_come.design_mode.facade;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class Starter {

    private Dashboard dashboard;

    private Engine engine;

    private SelfCheck selfCheck;

    public Starter() {
        this.dashboard = new Dashboard();
        this.engine = new Engine();
        this.selfCheck = new SelfCheck();
    }

    public void startUp() {
        System.out.println("car begin startup");
        engine.startup();
        dashboard.startup();
        selfCheck.startCheck();
        System.out.println("car startup finished");
    }

    public void shutdown() {
        System.out.println("car begin shutdown");
        selfCheck.shutdownCheck();
        engine.shutdown();
        dashboard.shutdown();
    }

    public static void main(String[] args) {
        Starter starter = new Starter();
        starter.startUp();
        System.out.println("*******************");
        starter.shutdown();

        /**
         * 输出结果：
         * car begin startup
         * engine startup......
         * dashboard startup......
         * startup check finished.
         * car startup finished
         * *******************
         * car begin shutdown
         * shutdown check finished
         * engine shutdown......
         * dashboard shutdown......
         *
         * 结果分析：
         *
         * 问题点答疑：
         */
    }
}
