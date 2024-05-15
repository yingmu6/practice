package thinking.combinate_class;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/21
 */
public class CADSystemExt {

    static class Shape {
       Shape(int i) { print("Shape constructor"); }
       void dispose() { print("Shape dispose"); }
    }

    static class Circle extends Shape {
        Circle(int i) {
            super(i);
            print("Drawing Circle");
        }
        void dispose() {
            print("Erasing Circle");
            super.dispose();
        }
    }

    static class Triangle extends Shape {
        Triangle(int i) {
            super(i);
        }
        void dispose() {
            print("Erasing Triangle");
            super.dispose();
        }
    }

    static class Line extends Shape {
        private int start, end;
        Line(int start, int end) {
            super(start);
            this.start = start;
            this.end = end;
            print("Drawing Line：" + start + "，" + end);
        }
        void dispose() {
            print("Erasing Line：" + start + "，" + end);
            super.dispose();
        }
    }
}
