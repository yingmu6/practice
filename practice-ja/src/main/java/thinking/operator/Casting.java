package thinking.operator;

/**
 * @author orange
 * @date 2024/6/11
 */
public class Casting {

    /**
     * 知识点（3.15）：类型转换操作符
     */
    public static void main(String[] args) {
        int i = 100;
        long lng = (long) i;
        lng = i;
        long lng2 = (long) 200;
        lng2 = 200;
        i = (int) lng2;
    }
}
