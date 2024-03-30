package thinking.polymorphism.case2;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class Square extends Shape {
    public void draw() {
        System.out.println("Square.draw()");
    }

    public void erase() {
        System.out.println("Square.erase()");
    }
}
