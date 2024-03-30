package thinking.polymorphism.case2;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class Shapes {

    private static RandomShapeGenerator gen = new RandomShapeGenerator();

    public static void main(String[] args) { //产生正确的行为
        Shape[] s = new Shape[9];
        for (int i = 0; i < s.length; i++) {
            s[i] = gen.next();
        }

        for(Shape shp : s) {
            shp.draw();
        }
    }
}
