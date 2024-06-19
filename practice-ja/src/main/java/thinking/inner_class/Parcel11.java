package thinking.inner_class;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class Parcel11 {

    protected static class ParcelContents implements Contents {
        private int i = 11;

        @Override
        public int value() {
            return i;
        }
    }

    protected static class ParcelDestination implements Destination {
        private String label;

        public ParcelDestination(String whereTo) {
            label = whereTo;
        }

        @Override
        public String readLabel() {
            return label;
        }

        public void fn() {
            System.out.println("ParcelDestination fn()");
        }

        static int x = 10;

        static class AnotherLevel { //ParcelDestination的静态内部类
            public void fn() {
                System.out.println("AnotherLevel fn()");
            }

            static int x = 10;

            public class Another2 {
                private String str;
                public Another2(String str) {
                    this.str = str;
                }
                public String getStr() {
                    return this.str;
                }
            }
        }
    }

    /**
     * 嵌套的内部类
     */
    public static void main(String[] args) {
        Parcel11.ParcelContents parcelContents = new Parcel11.ParcelContents();;
        System.out.println(parcelContents.value());

        Parcel11.ParcelDestination parcelDestination = new Parcel11.ParcelDestination("Tasmania");
        parcelDestination.fn();

        Parcel11.ParcelDestination.AnotherLevel anotherLevel = new Parcel11.ParcelDestination.AnotherLevel(); //嵌套的静态内部类
        anotherLevel.fn();

        Parcel11.ParcelDestination.AnotherLevel.Another2 another2 = anotherLevel.new Another2("haha"); //嵌套的成员内部类
        System.out.println(another2.getStr());

        /**
         *
         * 输出结果：
         * 11
         * ParcelDestination fn()
         * AnotherLevel fn()
         * haha
         *
         * 结果分析：
         * 1）ParcelContents和ParcelDestination都是Parcel11的静态内部类，而AnotherLevel又是ParcelDestination的静态内部类，
         *   实现了内部类之间的嵌套
         *
         * 2）Another2是AnotherLevel嵌套的成员内部类，先创建AnotherLevel的实例，然后再创建Another2的实例
         *
         * 总结概括：
         * 1）可以在类中实现多层的内部类嵌套，没有层级数的限制。
         *
         */
    }
}
