package thinking.inner_class;

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

    public static void main(String[] args) {
        Parcel7b p = new Parcel7b();
        Contents c = p.contents();

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
