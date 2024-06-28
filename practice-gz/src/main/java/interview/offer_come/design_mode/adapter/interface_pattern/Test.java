package interview.offer_come.design_mode.adapter.interface_pattern;

/**
 * @author chensy
 * @date 2024/3/14
 */
public class Test { //@MsY-Doing

    /**
     * 知识点：接口适配器
     *
     * 知识点概括：
     * 1）通过一个适配器类实现所有的接口方法，然后可以选择重写想处理的方法
     */

    public static void main(String[] args) { //Done
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
         * 1）AbstractAdapter实现了Sourceable所有方法，但是为空实现。
         *    子类可以按需实现对应的方法
         *
         */
    }
}
