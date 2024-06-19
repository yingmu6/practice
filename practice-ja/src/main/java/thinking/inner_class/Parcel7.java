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

    /**
     * 场景7：匿名内部类
     */
    public static void main(String[] args) {
        Parcel7 p = new Parcel7();
        Contents c = p.contents(); //此处c为匿名内部类，实例为：Parcel7$1@xxx
        System.out.println(c.value());

        /**
         * 输出结果：
         * 11
         *
         * 结果分析：
         * 1）在类Parcel7中contents()方法中，直接通过 new 接口名() {...}方式创建匿名内部类的实例
         *
         * 总结概括：
         * 1）匿名内部类是没有名称的，不用去新定义一个类
         */
    }
}
