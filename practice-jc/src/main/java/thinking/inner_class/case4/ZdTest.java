package thinking.inner_class.case4;

import org.junit.Test;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class ZdTest {

    /**
     * 知识点：解决多重继承 todo @Ym 用例的本意待了解
     *
     * 知识点概括：
     *
     * 问题点答疑：
     *
     */

    /**
     * 场景1：
     */
    @Test
    public void basicUse1() {
        Z z = new Z();
        MultiImplementation.takesD(z);
        MultiImplementation.takesE(z.makeE());

        /**
         * 输出结果：
         *
         * 结果分析：
         *
         * 结果概括：
         */
    }

    /**
     * 场景2：
     */
    @Test
    public void basicUse2() {
        X x = new X();
        Y y = new Y();
        MultiInterfaces.tasksA(x);
        MultiInterfaces.tasksA(y);
        MultiInterfaces.tasksB(x);
        MultiInterfaces.tasksB(y.makeB());

        /**
         * 输出结果：
         *
         * 结果分析：
         *
         * 结果概括：
         */
    }
}
