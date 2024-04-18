package thinking.polymorphism.shape;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class Triangle extends Shape {
    public void draw() {
        System.out.println("Triangle.draw()");
    }

    public void erase() {
        System.out.println("Triangle.erase()");
    }
}
