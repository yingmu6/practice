package thinking.inner_class;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class Parcel5 {
    /**
     * 在方法中的内部类
     */
    public Destination destination(String s) {

        class PDestination implements Destination {

            private String label;

            private PDestination(String whereTo) {
                this.label = whereTo;
            }

            @Override
            public String readLabel() {
                return this.label;
            }
        }

        return new PDestination(s);
    }

    /**
     * 在方法中的局部内部类
     */
    public static void main(String[] args) {
        Parcel5 p = new Parcel5();
        Destination d = p.destination("Tasmania");
        System.out.println(d.readLabel());

        /**
         * 输出结果：
         * Tasmania
         *
         * 结果分析：
         * 1）类Parcel5中的方法destination()中，定义了局部内部类PDestination，该类的作用范围在方法中。
         *
         * 总结概括：
         * 1）在方法中定义的类为局部内部类，可以通过这个内部类实现特定的逻辑
         */
    }
}
