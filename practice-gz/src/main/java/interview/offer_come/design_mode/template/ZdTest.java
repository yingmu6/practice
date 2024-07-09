package interview.offer_come.design_mode.template;

import org.junit.Test;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class ZdTest { //@MsY-Doing

    /**
     * 知识点：模版模式
     *
     * 知识点概括：
     *
     */

    /**
     * 场景1：基本使用
     */
    @Test
    public void basicUse() { //@Done
        AbstractTemplate template1 = new TakeMoney();
        template1.templateMethod();

        AbstractTemplate template2 = new SaveMoney();
        template2.templateMethod();

        /**
         * 输出结果：
         * checkNumber......
         * queue up......
         * take money form bank.
         * business finished, service evaluation......
         * checkNumber......
         * queue up......
         * save money to the bank.
         * business finished, service evaluation......
         *
         * 结果分析：
         * 1）将一组逻辑封装为模版方法，通用的逻辑放在抽象类中，而差异化的逻辑放在具体实现类中
         *    此处不管是取钱还是存钱业务，流程都是一样的，都要先取号、排队、业务处理、业务处理完成
         *    只是其中的取钱、存钱的业务不同，就交由TakeMoney、SaveMoney来具体实现了。
         *
         */
    }
}
