package interview.offer_come.design_mode.decorator;

/**
 * @author chensy
 * @date 2024/3/14
 */
public class Test {
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
         *
         * 问题点答疑：
         */
    }
}
