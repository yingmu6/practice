package thinking.combinate_class;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/21
 */
public class Bath { //@TkY-Doing

    /**
     * 知识点（7.1）：初始化的四种方式
     *
     * 知识点概要：
     * 1）变量的初始化方式有：
     *   a）定义时初始化，b）构造方法中初始化，c）块中初始化，d）toString中延迟初始化
     */
    static class Soap {
        private String s;
        Soap() {
            print("Soap()");
            s = "Constructed";
        }
        public String toString() { return s; }
    }

    private String
      s1 = "Happy",
      s2 = "Happy", //在定义时进行初始化
      s3, s4;

    private Soap castille;
    private int i;
    private float toy;
    public Bath() { //在构造方法中进行初始化
        print("Inside Bath()");
        s3 = "Joy";
        toy = 3.14f;
        castille = new Soap();
    }

    { i = 47; } //在块中进行初始化

    public String toString() {
        if (s4 == null) { //延迟初始化
            s4 = "Joy2";
        }
        return
          "s1 = " + s1 + "\n" +
          "s2 = " + s2 + "\n" +
          "s3 = " + s3 + "\n" +
          "s4 = " + s4 + "\n" +
          "i = " + i + "\n" +
          "toy = " + toy + "\n" +
          "castile = " + castille;
    }

    public static void main(String[] args) {
        Bath b = new Bath();
        print(b);

        /**
         * 运行结果：
         * Inside Bath()
         * Soap()
         * s1 = Happy
         * s2 = Happy
         * s3 = Joy
         * s4 = Joy2
         * i = 47
         * toy = 3.14
         * castile = Constructed
         *
         * 结果分析：
         * 1）在Bath创建对象时，在构造方法中进行了初始化，且构造方法执行后，会执行块初始化
         * 2）在进行对象打印时，会调用toString()方法，会对s4的值进行null判断，并对应赋值
         */
    }
}
