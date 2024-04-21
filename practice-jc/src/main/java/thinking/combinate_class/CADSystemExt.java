package thinking.combinate_class;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/21
 */
public class CADSystemExt {

    /**
     * 知识点（7.4.1）：确保正确清理
     */
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

    static public class CADSystem extends Shape {
        private Circle c;
        private Triangle t;
        private Line[] lines = new Line[3];
        public CADSystem(int i) {
            super(i + 1);
            for(int j = 0; j < lines.length; j++) {
                lines[j] = new Line(j, j * j);
            }
            c = new Circle(1);
            t = new Triangle(1);
            print("Combined constructor");
        }
        public void dispose() {
            print("CADSystem.dispose()");
            t.dispose();
            c.dispose();
            for (int i = lines.length -1; i >= 0; i--) {
                lines[i].dispose();
            }
            super.dispose();
        }

        public static void main(String[] args) {
            CADSystem x = new CADSystem(47);
            try {
                // 逻辑处理
            } finally {
                x.dispose();
            }
        }
    }
}
