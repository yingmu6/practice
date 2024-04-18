package thinking.inner_class;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class Parcel10 {
    public Destination destination(final String dest, final float price) {
        return new Destination() {
            private int cost;
            {
                cost = Math.round(price); //能访问外部类传入的参数
                if (cost > 100) {
                    System.out.println("Over budget!");
                }
            }

            private String label = dest;

            @Override
            public String readLabel() {
                return label;
            }
        };
    }

    /**
     * 场景10：匿名内部类
     */
    public static void main(String[] args) {
        Parcel10 p = new Parcel10();
        Destination d = p.destination("Tasmania", 101.395F);
        System.out.println(d.readLabel());

        /**
         *
         * 输出结果：
         * Over budget!
         * Tasmania
         *
         * 结果分析：
         * 1）在匿名类的代码块中，访问到外部类的方法入参值
         *
         * 总结概括：
         * 1）匿名类能访问外部类方法的入参
         *
         */
    }
}
