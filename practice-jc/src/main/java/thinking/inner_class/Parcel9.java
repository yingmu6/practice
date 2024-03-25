package thinking.inner_class;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class Parcel9 {

    /**
     * 匿名内部类：使用外部定义的对象（参数需要为final）
     */
    public Destination destination(final String dest) {
        return new Destination() {
            private String label = dest;
            @Override
            public String readLabel() {
                return label;
            }
        };
    }

    public static void main(String[] args) {
        Parcel9 p = new Parcel9();
        Destination d = p.destination("Tasmania");

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
