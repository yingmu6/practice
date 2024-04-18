package thinking.inner_class;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class Parcel8 {

    /**
     * 匿名内部类（调用基类中的方法）
     */
    public Wrapping wrapping(int x) {
        return new Wrapping(x) {
            public int value() {
                return super.value() * 47;
            }
        };
    }

    /**
     * 匿名类使用（调用基类中的方法）
     */
    public static void main(String[] args) {
        Parcel8 p = new Parcel8();
        Wrapping w = p.wrapping(10); //w对应的实例为：Parcel8$1@xxx
        System.out.println(w.value());

        Wrapping w2 = new Wrapping(12) { //w2对应的实例为：ZdTest$1@xxx
            @Override
            public int value() {
                return super.value() * 12; //通过super调用父类的方法
            }
        };
        System.out.println(w2.value());

        Wrapping w3 = new Wrapping(13); //w3对应的实例为：Wrapping@xxx
        System.out.println(w3.value());

        /**
         * 输出结果：
         * 470
         *
         * 结果分析：
         * 1）w、w2的类型都是匿名的内部类，可通过super访问父类Wrapping的方法
         * 2）w3是普通类Wrapping的实例
         *
         * 总结概括：
         * 1）匿名类可通过super访问父类的成员方法
         *
         */
    }
}
