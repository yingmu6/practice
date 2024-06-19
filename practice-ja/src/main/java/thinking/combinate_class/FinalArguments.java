package thinking.combinate_class;

/**
 * @author chensy
 * @date 2024/4/21
 */
public class FinalArguments {

    /**
     * 知识点（7.8）：final参数
     */
    class Gizmo {
        public void spin() {}
    }

    void with(final Gizmo g) {
       // g = new Gizmo(); //报不能赋值的语法异常
    }

    void without(Gizmo g) {
        g = new Gizmo();
        g.spin();
    }

    int g(final int i) { return i + 1; }

    public static void main(String[] args) {
        FinalArguments bf = new FinalArguments();
        bf.without(null);
        bf.with(null);
    }
}
