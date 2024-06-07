package thinking.io_relative;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author orange
 * @date 2024/6/7
 */
public class StoreCADState {

    /**
     * 知识点（18.12.3）：使用持久化
     */
    static abstract class Shape implements Serializable {
        public static final int RED = 1, BLUE = 2, GREEN = 3;
        private int xPos, yPos, dimension;
        private static Random rand = new Random(47);
        private static int counter = 0;
        public abstract void setColor(int newColor);
        public abstract int getColor();
        public Shape(int xVal, int yVal, int dim) {
            xPos = xVal;
            yPos = yVal;
            dimension = dim;
        }

        public String toString() {
            return getClass() +
                    "color[" + getColor() + "] xPos[" + xPos +
                    "] yPos[" + yPos + "] dim[" + dimension + "]\n";
        }
        public static Shape randomFactory() {
            int xVal = rand.nextInt(100);
            int yVal = rand.nextInt(100);
            int dim = rand.nextInt(100);
            switch (counter++ % 3) {
                default:
                case 0: return new Circle(xVal, yVal, dim);
                case 1: return new Square(xVal, yVal, dim);
                case 2: return new Line(xVal, yVal, dim);
            }
        }
    }

    static class Circle extends Shape {
        private static int color = RED;
        public Circle(int xVal, int yVal, int dim) {
            super(xVal, yVal, dim);
        }
        public void setColor(int newColor) { color = newColor; }
        public int getColor() { return color; }
    }

    static class Square extends Shape {
        private static int color;
        public Square(int xVal, int yVal, int dim) {
            super(xVal, yVal, dim);
        }
        public void setColor(int newColor) { color = newColor; }
        public int getColor() { return color; }
    }

    static class Line extends Shape {
        private static int color = RED;
        public static void serializeStaticState(ObjectOutputStream os) throws IOException {
            os.writeInt(color);
        }
        public static void deserializeStaticState(ObjectInputStream os) throws IOException {
            color = os.readInt();
        }
        public Line(int xVal, int yVal, int dim) {
            super(xVal, yVal, dim);
        }
        public void setColor(int newColor) { color = newColor; }
        public int getColor() { return color; }
    }

    public static void main(String[] args) throws IOException {
        List<Class<? extends  Shape>> shapeTypes =
                new ArrayList<Class<? extends Shape>>();
        shapeTypes.add(Circle.class);
        shapeTypes.add(Square.class);
        shapeTypes.add(Line.class);
        List<Shape> shapes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            shapes.add(Shape.randomFactory());
        }
        for (int i = 0; i < 10; i++) {
            ((Shape)shapes.get(i)).setColor(Shape.GREEN);
        }
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("CADState.out")
        );
        out.writeObject(shapeTypes);
        Line.serializeStaticState(out);
        out.writeObject(shapes);
        System.out.println(shapes);
    }
}
