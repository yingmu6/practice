package thinking.inner_class;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class Parcel6 {

    /**
     * 在作用域中的内部类
     */
    private void internalTracking(boolean b) {
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
            String s = ts.getSlip();
        }
    }

    public void track() {
        internalTracking(true);
    }

    public static void main(String[] args) {
        Parcel6 p = new Parcel6();
        p.track();

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
