package thinking.polymorphism;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class ReferenceCounting { //@TkY-Doing

    /**
     * 知识点：引用计数追踪仍旧访问者着共享对象的对象数量
     *
     * 知识点概括：
     * 1）
     *
     * 问题点答疑：
     * 1）测试用例的本意，是需要表达什么？？
     */
    static class Shared {
        private int refcount = 0;
        private static long counter = 0; //静态变量（对象之间共享）
        private final long id = counter++;
        public Shared() {
            System.out.println("Creating " + this);
        }
        public void addRef() { refcount++; }
        protected void dispose() {
            if (--refcount == 0)
                System.out.println("Finish disposing " + this);
        }
        public String toString() { return "Shared " + id; }
    }

    static class Composing {
        private Shared shared;
        private static long counter = 0;
        private final long id = counter++;
        public Composing(Shared shared) {
            System.out.println("Creating " + this);
            this.shared = shared;
            this.shared.addRef();
        }
        protected void dispose() {
            System.out.println("disposing " + this);
            shared.dispose();
        }
        public String toString() {
            return "Composing " + id;
        }
    }

    public static void main(String[] args) {
        Shared shared = new Shared();
        Composing[] composing = {
                new Composing(shared),
                new Composing(shared),
                new Composing(shared),
                new Composing(shared),
                new Composing(shared)
        };

        for (Composing c : composing) {
            c.dispose();
        }

        /**
         * 输出结果：
         * Creating Shared 0
         * Creating Composing 0
         * Creating Composing 1
         * Creating Composing 2
         * Creating Composing 3
         * Creating Composing 4
         * disposing Composing 0
         * disposing Composing 1
         * disposing Composing 2
         * disposing Composing 3
         * disposing Composing 4
         * Finish disposing Shared 0
         *
         * 结果分析：
         * 1）Creating Shared 0 分析：
         *    创建Shared对象时，对成员变量计算：id = counter++; 因为此时类变量counter为0，所以id = 0
         *
         * 2）Creating Composing 0 ... 4 分析：
         *    创建Composing对象时，构造方法中传入同一个对象Shared，对 id = counter++，id值根据类变量counter计算，所以依次为0~4
         *    然后对共享对象Shared进行shared.addRef()操作，所以Shared对象中的refcount也在累加
         *
         * 3）disposing Composing 0 ... 4 分析：
         *    调用Composing时，会调用shared.dispose(); 然后toString输出的id，即为Shared中的id
         *
         * 4）Finish disposing Shared 0 分析：
         *    Composing创建时，都会调用shared.addRef()对refcount进行累加，而在Composing#dispose()时，refcount的值会进行累减
         *
         */
    }
}
