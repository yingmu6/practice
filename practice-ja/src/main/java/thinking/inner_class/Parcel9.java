package thinking.inner_class;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class Parcel9 {

    /**
     * 匿名内部类
     */
    public Destination destination(String dest) {
        return new Destination() {
            private String label = dest;
            @Override
            public String readLabel() {
                return label;
            }
        };
    }

    /**
     * 场景9：匿名内部类使用
     */
    public static void main(String[] args) {
        Parcel9 p = new Parcel9();
        Destination d = p.destination("Tasmania");
        System.out.println(d.readLabel());

        /**
         * 输出结果：
         * Tasmania
         *
         * 结果分析：
         * 1）与上面用例类似
         *
         * 总结概括：
         * 1）与上面用例类似
         *
         */
    }
}
