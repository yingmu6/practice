package thinking.io_relative;

import net.mindview.util.OSExecute;

/**
 * @author orange
 * @date 2024/6/7
 */
public class OSExecuteDemo {

    /**
     * 知识点（18.9）：进程控制
     */
    public static void main(String[] args) {
        OSExecute.command("javap OSExecuteDemo");
    }
}
