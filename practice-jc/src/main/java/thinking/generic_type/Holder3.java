package thinking.generic_type;

/**
 * @author chensy
 * @date 2024/5/17
 */
public class Holder3<T> {

    /**
     * 知识点（15.2）：简单泛型
     */
    private T a;
    public Holder3(T a) { this.a = a; }
    public void set(T a) { this.a = a; }
    public T get() { return a; }

    public static void main(String[] args) {
        Holder3<Automobile> h3 = new Holder3<>(new Automobile());
        Automobile a = h3.get(); //此处不需要强制转换，因为创建对象时，已经指明泛型了
        // h3.set("Not an Automobile"); //编译错误，因为泛型已经指明为Automobile，不能为其它类型
        // h3.set(1);
    }
}
