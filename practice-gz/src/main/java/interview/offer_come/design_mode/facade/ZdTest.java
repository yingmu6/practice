package interview.offer_come.design_mode.facade;

import org.junit.Test;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class ZdTest { //@MsY-Doing

    /**
     * 知识点：外观模式
     *
     * 知识点概括：
     * 1）facade外观模式（门面模式），对外只提供组合功能的方法，具体的组装逻辑放在内部。
     *
     * 问题点答疑：
     *
     */

    /**
     * 场景1：外观模式基本使用
     */
    @Test
    public void basicUse() { //Doing
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
         * 1）Starter对象创建时，会创建汽车启动/停止关联的Engine、Dashboard、SelfCheck对象实例
         * 2）Starter对外只提供startUp()、shutdown()方法，具体启动、停止的逻辑都在Starter里面组装，对外隐藏了逻辑
         *
         */
    }
}
