package thinking.polymorphism.case6;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class Description {

    private String s;

    Description(String s) {
        this.s = s;
        System.out.println("Creating Description " + s);
    }

    protected void dispose() {
        System.out.println("disposing Description " + s);
    }
}
