package thinking.polymorphism.case2;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class Shapes {

    /**
     * 知识点：产生正确的行为
     */
    private static RandomShapeGenerator gen = new RandomShapeGenerator();

    public static void main(String[] args) {
        Shape[] s = new Shape[9];
        for (int i = 0; i < s.length; i++) {
            s[i] = gen.next();
        }

        for(Shape shp : s) {
            shp.draw();
        }

        /**
         * 输出结果：（运行结果会变：因为是根据随机数选择执行的）
         * Square.draw()
         * Square.draw()
         * Circle.draw()
         * Circle.draw()
         * Triangle.draw()
         * Circle.draw()
         * Square.draw()
         * Circle.draw()
         * Triangle.draw()
         *
         * 结果分析：
         * 1）Square、Circle、Triangle都是Shape的子类，在执行gen.next()会根据随机值创建不同的实例
         *
         * 2）执行Shape[]的数组循环时，会根据所存储的具体对象实例来执行相应对象的方法
         */
    }
}
