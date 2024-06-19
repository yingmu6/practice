package thinking.inner_class;

import org.junit.Test;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class Parcel6 {

    /**
     * 在作用域中的内部类
     */
    public String internalTracking(boolean b) {
        if (b) {
            class TrackingSlip {
                private String id;
                TrackingSlip(String s) {
                    this.id = s;
                }
                String getSlip() {
                    return this.id;
                }
            }

            TrackingSlip ts = new TrackingSlip("slip");
            return ts.getSlip();
        } else {
            return null;
        }

        // TrackingSlip ts2 = new TrackingSlip("slip"); //此处会编译错误，因为超出了TrackingSlip的作用范围
    }

    /**
     * 在代码块中的局部内部类
     */
    public static void main(String[] args) {
        Parcel6 p = new Parcel6();
        System.out.println(p.internalTracking(true));

        /**
         * 输出结果：
         * slip
         *
         * 结果分析：
         * 1）局部内部类TrackingSlip在if语句的代码块中
         *
         * 总结概括：
         * 1）代码块中的内部类的作用范围就只是代码块中，不能超出
         */
    }
}
