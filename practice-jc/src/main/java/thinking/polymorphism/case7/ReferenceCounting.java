package thinking.polymorphism.case7;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class ReferenceCounting {

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
