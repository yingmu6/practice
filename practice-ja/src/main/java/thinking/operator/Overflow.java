package thinking.operator;

/**
 * @author orange
 * @date 2024/6/11
 */
public class Overflow {

    /**
     * 知识点（3.17）：结果溢出
     */
    public static void main(String[] args) {
        int big = Integer.MAX_VALUE;
        System.out.println("big = " + big);
        int bigger = big * 4;
        System.out.println("bigger = " + bigger);
    }
}
