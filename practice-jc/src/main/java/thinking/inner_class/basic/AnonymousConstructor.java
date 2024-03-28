package thinking.inner_class.basic;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class AnonymousConstructor {

    public static Base getBase(int i) {
        return new Base(i) {
            {
                System.out.println("Inside instance initializer");
            }
            @Override
            public void fn() {
                System.out.println("In anonymous f()");
            }
        };
    }
}
