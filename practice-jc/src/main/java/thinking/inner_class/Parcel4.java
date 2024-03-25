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
        Parcel4.PContents c1 = p.new PContents();

        Destination d = p.destination("Tasmania");
        Parcel4.PDestination d1 = p.new PDestination("Tasmania");

        /**
         * 输出结果：
         * （输出空白内容）
         *
         * 结果分析：
         * 1）通过成员方法构造成员内部类的实例并返回
         * 2）通过直接new的方式创建内部类的实例并返回
         *
         * 总结概括：
         */
    }
}
