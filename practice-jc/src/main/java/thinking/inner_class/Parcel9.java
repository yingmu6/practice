package thinking.inner_class;

import thinking.inner_class.basic.Destination;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class Parcel9 {

    /**
     * 匿名内部类
     */
    public Destination destination(String dest) {
        return new Destination() {
            private String label = dest;
            @Override
            public String readLabel() {
                return label;
            }
        };
    }
}
