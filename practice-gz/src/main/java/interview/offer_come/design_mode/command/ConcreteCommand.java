package interview.offer_come.design_mode.command;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class ConcreteCommand implements Command { //@MsY-Doing

    /**
     * 知识点：命令模式
     *
     * 知识点概括：
     * 1）
     */

    private Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void exe(String command) {
        receiver.action(command);
    }

    public static void main(String[] args) {
        Receiver receiver1 = new Receiver();
        Command cmd = new ConcreteCommand(receiver1);
        Invoker invoker = new Invoker(cmd);
        invoker.action("command1");

        /**
         * 输出结果：
         * command sending...
         * command received, now execute command
         *
         * 结果分析：
         * 1）
         *
         * 问题点答疑：
         * 1）命令模式都包含哪些角色？
         */
    }
}
