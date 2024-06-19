package thinking.generic_type;

/**
 * @author chensy
 * @date 2024/5/18
 */
public class Manipulation {

    /**
     * 知识点（15.7.1）
     */

    static class HasF {
        public void f() {
            System.out.println("HasF.f()");
        }
    }

    static class Manipulator<T> {
        private T obj;
        public Manipulator(T x) {
            obj = x;
        }
        public void manipulate() {
            // obj.f(); //会报找不到f()方法的编译错误
        }
    }

    static class Manipulator2<T extends HasF> {
        private T obj;
        public Manipulator2(T x) {
            obj = x;
        }
        public void manipulate() {
            obj.f(); //此处能正常编译
        }
    }

    public static void main(String[] args) {
        HasF hf = new HasF();
        Manipulator<HasF> manipulator = new Manipulator<HasF>(hf);
        manipulator.manipulate();
    }
}
