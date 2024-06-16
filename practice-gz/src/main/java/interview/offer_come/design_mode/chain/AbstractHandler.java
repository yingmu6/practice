package interview.offer_come.design_mode.chain;

/**
 * @author chensy
 * @date 2024/3/15
 */
public abstract class AbstractHandler {

    private Handler handler;

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
