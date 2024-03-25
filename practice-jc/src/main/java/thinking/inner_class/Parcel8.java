package thinking.inner_class;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class Parcel8 {

    /**
     * 匿名内部类（调用基类的构造器）
     */
    public Wrapping wrapping(int x) {
        return new Wrapping(x) {
            public int value() {
                return super.value() * 47;
            }
        };
    }

    public static void main(String[] args) {
        Parcel8 p = new Parcel8();
        Wrapping w = p.wrapping(10);

        /**
         * 输出结果：
         * （输出空白内容）
         *
         * 结果分析：
         *
         * 问题点答疑：
         */
    }
}
