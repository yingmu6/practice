package thinking.inner_class;

import thinking.inner_class.basic.Wrapping;

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
}
