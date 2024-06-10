package thinking.operator;

/**
 * @author orange
 * @date 2024/6/8
 */
public class EqualsMethod2 {

    /**
     * 知识点（3.7.1）：对象的等价性
     */
    static class Value {
        int i;
    }

    public static void main(String[] args) {
        Value v1 = new Value();
        Value v2 = new Value();
        v1.i = v2.i = 100;
        System.out.println(v1.equals(v2));
    }
}
