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

    public static void main(String[] args) {
        Parcel5 p = new Parcel5();
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
