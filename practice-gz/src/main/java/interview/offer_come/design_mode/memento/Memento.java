package interview.offer_come.design_mode.memento;

import org.junit.Test;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class Memento { //@MsY-Doing

    /**
     * 知识点：
     *
     * 知识点概括：
     * 1）
     */

    private String value;

    public Memento(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static void main(String[] args) { //Doing
        Original original = new Original("张三");
        Storage storage = new Storage(original.createMemento());
        System.out.println("original value: " + original.getValue());
        original.setValue("李四");
        System.out.println("update value: " + original.getValue());
        original.setValue("王五");
        System.out.println("update value: " + original.getValue());
        original.restoreMemento(storage.getMemento());
        System.out.println("restore value:" + original.getValue());

        /**
         * 输出结果：
         * original value: 张三
         * update value: 李四
         * update value: 王五
         * restore value:张三
         *
         * 结果分析：
         * 1）原始对象Original的值放入备忘对象Memento中，并存储在Storage中
         *   在原始对象的值想恢复的最原始的值时，就使用备忘录中的值来做恢复
         *
         * 2）这种备忘录，只存储了一个最原始备忘录值，也就是多次更新后，只能更新到最初的值
         *    不能回归到某一次变更值。像git那样的版本管控，是可以恢复到某个版本的值的
         */
    }

    /**
     * 新增场景：可回退到指定版本的备忘值
     */
    @Test
    public void mementoWithVersion() {
        System.out.println(11);
    }
}
