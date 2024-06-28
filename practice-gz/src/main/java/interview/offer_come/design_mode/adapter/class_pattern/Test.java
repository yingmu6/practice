package interview.offer_come.design_mode.adapter.class_pattern;
/**
 *
 * @author chensy
 * @date 2024/3/14
 */
public class Test { //@MsY-Doing

    /**
     * 知识点：类的适配器
     *
     * 知识点概括：
     * 1）通过继承其它类的方式，获取目标接口的方法实现
     */

    public static void main(String[] args) { //Done
       Targetable target = new Adapter();
       target.editTextFile();
       target.editWordFile();

        /**
         * 输出结果：
         * a text file editing
         * a word file editing
         *
         * 结果分析：
         * 1）接口Targetable中有两个方法，实现接口就要实现接口中方法。
         *    Adapter实现了其中的editWordFile方法，而另一个方法通过继承Source间接实现
         */
    }
}
