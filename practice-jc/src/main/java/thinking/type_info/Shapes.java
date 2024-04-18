package thinking.type_info;

import java.util.Arrays;
import java.util.List;

/**
 * @author chensy
 * @date 2024/4/8
 */
public class Shapes {

    /**
     * 知识点：为什么需要RTTI
     * 1） RTTI（Run-Time Type Identification，运行时类型识别）的含义就是在运行时识别一个对象的类型，其对应的类是Class对象
     * 2）无论是RTTI还是反射，其本质都是一样的，都是去动态的获取类的信息，他们唯一的区别仅是:
     *    a）RTTI 在编译期知道要解析的类型。
     *    b）反射 在运行期知道要解析的类型。
     * 3）RTTI的概念是在《Thinking in Java》中提到的，才引来这么多人的讨论，原生的Java中并没有这个概念的说法。
     *    所以，我们根本不必纠结是RTTI还是反射，他们无论用法还是本质都是一样的，都是为了实现一样的目的——动态的获取类的信息
     */

    static abstract class Shape {
        void draw() {
            System.out.println(this + ".draw()");
        }
        abstract public String toString();
    }

    static class Candy {
        static {
            System.out.println("Loading Candy");
        }
    }

    static class Circle extends Shape {
        @Override
        public String toString() {
            return "Circle";
        }
    }

    static class Square extends Shape {
        @Override
        public String toString() {
            return "Square";
        }
    }

    static class Triangle extends Shape {
        @Override
        public String toString() {
            return "Triangle";
        }
    }

    public static void main(String[] args) {
        List<Shape> shapeList = Arrays.asList(
                new Circle(), new Square(), new Triangle()
        );

        for (Shape shape : shapeList) {
            shape.draw();
        }
    }
}
