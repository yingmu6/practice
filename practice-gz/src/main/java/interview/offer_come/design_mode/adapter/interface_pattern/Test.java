package interview.offer_come.design_mode.adapter.interface_pattern;

/**
 * @author chensy
 * @date 2024/3/14
 */
public class Test {
    public static void main(String[] args) {
        Sourceable source1 = new SourceSub1();
        Sourceable source2 = new SourceSub2();

        source1.editTextFile();
        source2.editWordFile();

        /**
         * 输出结果：
         * a text file editing
         * a word file editing
         *
         * 结果分析：
         *
         * 问题点答疑：
         */
    }
}
