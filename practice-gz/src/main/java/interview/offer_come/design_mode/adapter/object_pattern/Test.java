package interview.offer_come.design_mode.adapter.object_pattern;

import interview.offer_come.design_mode.adapter.class_pattern.Source;
import interview.offer_come.design_mode.adapter.class_pattern.Targetable;

/**
 * @author chensy
 * @date 2024/3/14
 */
public class Test { //@MsY-Doing

    /**
     * 知识点：对象适配器
     *
     * 知识点概括：
     * 1）通过组合对象来实现目标接口的方法
     */

    public static void main(String[] args) { //Done
        Source source = new Source();
        Targetable target = new ObjectAdapter(source);
        target.editWordFile();
        target.editTextFile();

        /**
         * 输出结果：
         * a word file editing
         * a text file editing
         *
         * 结果分析：
         * 1）ObjectAdapter实现了Targetable接口的方法，但是部分实现
         *    来自内部持有对象的方法
         */
    }
}
