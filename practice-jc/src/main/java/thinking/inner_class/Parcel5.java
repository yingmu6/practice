package thinking.inner_class;

import thinking.inner_class.basic.Destination;

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
}
