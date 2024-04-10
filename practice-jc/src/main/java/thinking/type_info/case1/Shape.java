package thinking.type_info.case1;

/**
 * @author chensy
 * @date 2024/4/8
 */
abstract class Shape {

    void draw() {
        System.out.println(this + ".draw()");
    }

    abstract public String toString();
}
