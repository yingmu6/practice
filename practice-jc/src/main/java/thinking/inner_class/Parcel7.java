package thinking.inner_class;

import thinking.inner_class.basic.Contents;

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
}
