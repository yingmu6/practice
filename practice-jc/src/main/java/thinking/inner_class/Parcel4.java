package thinking.inner_class;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class Parcel4 {

    private class PContents implements Contents {
        private int i = 11;

        @Override
        public int value() {
            return i;
        }
    }

    protected class PDestination implements Destination {
        private String label;

        private PDestination(String whereTo) {
            label = whereTo;
        }

        @Override
        public String readLabel() {
            return label;
        }
    }

    public Destination destination(String s) {
        return new PDestination(s);
    }

    public Contents contents() {
        return new PContents();
    }

    /**
     * 内部类的创建方式
     */
    public static void main(String[] args) {
        Parcel4 p = new Parcel4();

        // 方式1：
        Contents c = p.contents();

        // 方式2：
//        Parcel4.PContents c1 = p.new PContents(); //此处编译报错：因为PContents是private的，不可访问

        Destination d = p.destination("Tasmania");
        System.out.println(d.readLabel());
//        Parcel4.PDestination d1 = p.new PDestination("Tasmania"); //此处报编译错误，因为PDestination的构造方法是private，不能访问的

        /**
         * 输出结果：
         * Tasmania
         *
         * 结果分析：
         * 1）通过成员方法构造成员内部类的实例并返回
         * 2）通过直接new的方式创建内部类的实例并返回
         *
         * 总结概括：
         * 1）普通的类，是不能加上private、protected等修饰的，而内部类就可以，可以通过这个特性实现内部细节隐藏
         */
    }
}
