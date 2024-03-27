package thinking.inner_class;

import thinking.inner_class.basic.Contents;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class Parcel7b {

    /**
     * Parcel7的扩展
     */
    class MyContents implements Contents {
        int i = 11;
        @Override
        public int value() {
            return i;
        }
    }

    public Contents contents() {
        return new MyContents();
    }
}
