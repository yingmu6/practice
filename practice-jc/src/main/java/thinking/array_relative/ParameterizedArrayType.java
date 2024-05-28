package thinking.array_relative;

/**
 * @author orange
 * @date 2024/5/28
 */
public class ParameterizedArrayType {

    /**
     * 知识点（16.5）：数组与泛型
     *
     * 知识点概括：
     * 1）
     */

    static class ClassParameter<T> {
        public T[] f(T[] arg) { return arg; }
    }

    static class MethodParameter {
        public static <T> T[] f(T[] arg) { return arg; }
    }

    public static void main(String[] args) {
        Integer[] ints = { 1, 2, 3, 4, 5 };
        Double[] doubles = { 1.1, 2.2, 3.3, 4.4, 5.5 };
        Integer[] ints2 =
                new ClassParameter<Integer>().f(ints);
        Double[] doubles2 =
                new ClassParameter<Double>().f(doubles);
        ints2 = MethodParameter.f(ints);
        doubles2 = MethodParameter.f(doubles);
    }
}
