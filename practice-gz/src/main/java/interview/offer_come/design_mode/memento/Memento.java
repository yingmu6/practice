package interview.offer_come.design_mode.memento;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class Memento {

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

    public static void main(String[] args) {
        Original original = new Original("张三");
        Storage storage = new Storage(original.createMemento());
        System.out.println("original value: " + original.getValue());
        original.setValue("李四");
        System.out.println("update value: " + original.getValue());
        original.restoreMemento(storage.getMemento());
        System.out.println("restore value:" + original.getValue());

        /**
         * 输出结果：
         * original value: 张三
         * update value: 李四
         * restore value:张三
         *
         * 结果分析：
         *
         * 问题点答疑：
         */
    }
}
