package interview.written_exam.basic.inherit_ext;

/**
 * @author chensy
 * @date 2023/7/3
 */
public class Child extends Parent {

    public static String childStaticString="child静态变量";

    static {
        System.out.println("child:静态代码块:"+childStaticString);
    }

    static { //静态块之间执行顺序，按声明的顺序
        System.out.println("child:静态代码块1");
    }

    {
        System.out.println("child:代码块");
    }
    private String aa="child成员变量";

    public Child() {
        super();
        System.out.println("child:构造函数"+this.aa);
    }
}
