package interview.offer_come.design_mode.state;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class Context {

    private AbstractState state;

    public Context(AbstractState state) {
        this.state = state;
    }

    public void setState(AbstractState state) {
        this.state = state;
    }

    public AbstractState getState() {
        return state;
    }

    public void action() {
        this.state.action(this);
    }


    public static void main(String[] args) {
        Context context = new Context(new WorkState());
        context.action();
        context.setState(new HolidayState());
        context.action();

        /**
         * 输出结果：
         * state change to work state
         * work state action is meeting，design，coding...
         * state change to holiday state
         * holiday state action is travel, shopping, watch, television...
         *
         * 结果分析：
         *
         * 问题点答疑：
         */
    }
}
