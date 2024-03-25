package thinking.inner_class;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class Parcel7 {
    /**
     * 匿名内部类
     */
    public Contents contents() {
        return new Contents() {
            private int i = 11;
            @Override
            public int value() {
                return i;
            }
        };
    }

    public static void main(String[] args) {
       Parcel7 p = new Parcel7();
       Contents c = p.contents();

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
