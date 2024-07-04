package interview.offer_come.design_mode.facade;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class Starter {

    private Dashboard dashboard;

    private Engine engine;

    private SelfCheck selfCheck;

    public Starter() { //初始化
        this.dashboard = new Dashboard();
        this.engine = new Engine();
        this.selfCheck = new SelfCheck();
    }

    public void startUp() { //启动项
        System.out.println("car begin startup");
        engine.startup();
        dashboard.startup();
        selfCheck.startCheck();
        System.out.println("car startup finished");
    }

    public void shutdown() { //停止项
        System.out.println("car begin shutdown");
        selfCheck.shutdownCheck();
        engine.shutdown();
        dashboard.shutdown();
    }
}
