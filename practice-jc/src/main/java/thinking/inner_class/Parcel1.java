package thinking.inner_class;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class Parcel1 { //@TkY-Doing

    /**
     * 知识点：内部类
     *
     * 知识点概括：
     * 1）内部类的作用有：
     *    a）隐藏细节：内部类可用通过private、protected等对类进行修饰，来实现信息的隐藏。
     *    b）使用外部类的所有元素：作为外部内的一个成员，可以访问外部类都所有信息（若是static内部类，就只能访问外部类static成员变量）
     *    c）可以实现多重继承：这个特点非常重要，它是内部类存在的最大理由之一。就是可以在类中声明多个内部类，然后这些内部类都继承了其它的类，
     *                     然后把其他类的功能组合到当前类中了
     *    d）可以通过匿名内部类优化简单接口实现：不需要定义新的类，直接通过匿名内部类来创建对象。
     *
     * 2）外部类和内部类都会产生对应的class字节码文件，内部类的字节码文件名称："外部类名$内部类名"
     *
     * 3）内部类的创建方式：
     *    a）若是静态内部类，可通过 "new 外部类.内部类()"
     *    b）若不是静态内部类，先创建外部类的实例，然后用 "外部类实例.new 内部类()"
     *
     * 相关点学习：
     * 1）
     *
     * 问题点答疑：
     * 1）成员内部类，若没指定访问权限，在其它的类中，要怎么访问，如Contents、Destination
     *
     * 参考链接：
     * 1）https://worktile.com/kb/p/37738#
     */

    class Contents { //成员内部类
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

    public void ship(String dest) {
        Contents c = new Contents();
        Destination d = new Destination(dest);
        System.out.println(d.readLabel());
    }

    /**
     * 测试成员内部类的基本使用
     */
    public static void main(String[] args) {
        Parcel1 p = new Parcel1();
        p.ship("Tasmania");

        /**
         * 输出结果：
         * Tasmania
         *
         * 结果分析：
         * 1）先创建外部类Parcel1的对象，然后调用成员方法ship()，该方法中包含了对成员内部类的创建及访问
         *
         * 总结概括：
         * 1）成员内部类的作用位置和成员变量、成员方法相同，可当做成员变量来使用
         */
    }
}
