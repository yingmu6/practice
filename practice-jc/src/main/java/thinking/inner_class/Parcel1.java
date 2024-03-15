package thinking.inner_class;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class Parcel1 {

    class Contents {
        private int i = 11;
        public int value() {
            return i;
        }
    }

    class Destination {
        private String label;
        Destination(String whereTo) {
            label = whereTo;
        }
        String readLabel() {
            return label;
        }
    }

    public void ship(String dest) {
        Contents c = new Contents();
        Destination d = new Destination(dest);
        System.out.println(d.readLabel());
    }

    public static void main(String[] args) {
        Parcel1 p = new Parcel1();
        p.ship("Tasmania");

        /**
         * 输出结果：
         * Tasmania
         *
         * 结果分析：
         *
         * 问题点答疑：
         */
    }
}
