package thinking.holder_obj;

import java.util.ArrayList;

/**
 * @author orange
 * @date 2024/6/5
 */
public class ApplesAndOrangesWithGenerics {

    /**
     * 知识点（11.1）：泛型和类型安全的容器
     */
    public static void main(String[] args) {
        ArrayList<AppleAndOrangesWithoutGenerics.Apple> apples = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            apples.add(new AppleAndOrangesWithoutGenerics.Apple());
        }

        for (int i = 0; i < apples.size(); i++) {
            System.out.println(apples.get(i).id());
        }

        for (AppleAndOrangesWithoutGenerics.Apple c : apples) {
            System.out.println(c.id());
        }
    }
}
