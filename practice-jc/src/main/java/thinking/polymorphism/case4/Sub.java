package thinking.polymorphism.case4;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class Sub extends Super {

    public int field = 1;

    public int getField() {
        return field;
    }

    public int getSuperField() {
        return super.field;
    }
}
