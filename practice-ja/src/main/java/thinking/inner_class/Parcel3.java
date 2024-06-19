package thinking.inner_class;

import org.junit.Test;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class Parcel3 {

    class Contents {
        private int i = 11;

        public int value() {
            return i;
        }
    }

    class Destination {
        private String label;

        Destination(String whereTo) {
            label = whereTo;
        }

        String readLabel() {
            return label;
        }
    }

    /**
     * 成员内部类实例的创建（通过外部对象实例new）
     */
    public static void main(String[] args) {
        Parcel3 p = new Parcel3();
        Parcel3.Contents c = p.new Contents();
        Parcel3.Destination d = p.new Destination("Tasmania");
        System.out.println(d.readLabel());

        /**
         * 输出结果：
         * Tasmania
         *
         * 结果分析：
         * 1）先创建外部类Parcel3的实例，再通过p.new 方式创建成员内部类的实例
         *
         * 总结概括：
         * 1）可通过外部类的实例，创建内部类实例，语法为：外部类实例引用.new 内部类构造方法
         */
    }
}
