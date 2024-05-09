package thinking.annotation;

import net.mindview.atunit.Test;

/**
 * @author chensy
 * @date 2024/5/3
 */
public class Testable {

    /**
     * 知识点（20.1）：注解基本语法
     */
    public void execute() {
        System.out.println("Executing..");
    }

    @Test  //作用上方法上
    void testExecute() {
        execute();
    }
}
