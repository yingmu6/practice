package thinking.polymorphism;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class ReferenceCounting {

    static class Shared {
        private int refcount = 0;
        private static long counter = 0;
        private final long id = counter++;
        public Shared() {
            System.out.println("Creating " + this);
        }
        public void addRef() { refcount++; }
        protected void dispose() {
            if (--refcount == 0)
                System.out.println("Disposing " + this);
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

    public static void main(String[] args) { //引用计数追踪仍旧访问者着共享对象的对象数量
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
    }
}
