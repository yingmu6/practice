package thinking.inner_class;

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
}
