package thinking.operator;

/**
 * @author orange
 * @date 2024/6/8
 */
public class Exponents {

    /**
     * 知识点（3.9.1）：指数记数法
     */
    public static void main(String[] args) {
        float expFloat = 1.39e-43f;
//        expFloat = 1.39E - 43f; //编译错误
        System.out.println(expFloat);
        double expDouble = 47e47d;
        double expDouble2 = 47e47;
        System.out.println(expDouble);
    }
}
