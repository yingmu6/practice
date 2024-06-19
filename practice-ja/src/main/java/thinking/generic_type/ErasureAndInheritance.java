package thinking.generic_type;

/**
 * @author chensy
 * @date 2024/5/18
 */
public class ErasureAndInheritance {

    /**
     * 知识点（15.7.3）：擦除的问题
     */

    static class GenericBase<T> {
        private T elment;
        public void set(T arg) { arg = elment; }
        public T get() { return elment; }
    }

    static class Derived1<T> extends GenericBase<T> {}

    static class Derived2 extends GenericBase {} //No warning

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Derived2 d2 = new Derived2();
        Object obj = d2.get();
        d2.set(obj);
    }
}
