package interview.offer_come.design_mode.decorator;

/**
 * @author chensy
 * @date 2024/3/14
 */
public class Test { //@MsY-Doing

    /**
     * 知识点：
     *
     * 知识点概括：
     * 1）
     *
     */

    public static void main(String[] args) {
        Sourceable source = new Source();
        Sourceable obj = new Decorator(source);
        obj.createComputer();

        /**
         * 输出结果：
         * create computer by source
         * make system.
         *
         * 结果分析：
         * 1）Decorator持有source引用，在不改变Sourceable行为的情况下，可以对其行为进行增强。
         */
    }
}
