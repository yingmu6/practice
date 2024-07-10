package interview.offer_come.design_mode.state;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class Context { //@MsY-Doing

    /**
     * 知识点：
     *
     * 问题点答疑：
     * 1）状态模式主要的应用场景是怎样的？
     *
     */

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


    public static void main(String[] args) { //Done
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
         * 1）在创建Context时，设置了成员变量state的实例为WorkState，所以第1次调用action
         *    时，执行的是WorkState的action()行为
         *
         * 2）在setState(AbstractState state)中，更改了state的实例为HolidayState
         *    所以第2次调用action时，执行的是HolidayState的action()行为
         *
         */
    }
}
