package thinking.holder_obj;

import java.util.ArrayList;

/**
 * @author orange
 * @date 2024/6/5
 */
public class GenericsAndUpcasting {

    /**
     * 知识点（11.1）：
     */
    static class GrannySimith extends AppleAndOrangesWithoutGenerics.Apple {}

    static class Gala extends AppleAndOrangesWithoutGenerics.Apple {}

    static class Fuji extends AppleAndOrangesWithoutGenerics.Apple {}

    static class Braeburn extends AppleAndOrangesWithoutGenerics.Apple {}

    public static void main(String[] args) {
        ArrayList<AppleAndOrangesWithoutGenerics.Apple> apples = new ArrayList<>();
        apples.add(new GrannySimith());
        apples.add(new Gala());
        apples.add(new Fuji());
        apples.add(new Braeburn());
        for (AppleAndOrangesWithoutGenerics.Apple c : apples) {
            System.out.println(c);
        }
    }
}
