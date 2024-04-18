package thinking.inner_class;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class Parcel7b {

    /**
     * Parcel7的扩展
     */
    class MyContents implements Contents {
        int i = 11;
        @Override
        public int value() {
            return i;
        }
    }

    public Contents contents() {
        return new MyContents();
    }

    /**
     * 指定名称的内部类
     */
    public static void main(String[] args) {
        Parcel7b p = new Parcel7b();
        Contents c = p.contents();
        System.out.println(c.value()); //此处c的实例为：Parcel7b$MyContents

        /**
         * 输出结果：
         * 11
         *
         * 结果分析：
         * 1）此处Parcel7b中的MyContents，是指定名称的
         *
         * 总结概括：
         * 1）未指定名称的内部类为匿名内部类
         */
    }
}
