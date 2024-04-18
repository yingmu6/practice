package thinking.enum_type;

/**
 * @author chensy
 * @date 2024/4/8
 */
public class Burrito { //@thinking Done

    /**
     * 知识点：将静态导入用于enum
     * 1）所有的枚举类型都默认继承了java.lang.Enum类（所有就继承了该类中的所有方法，可通过debug调试）
     *
     * 2）enum与class、interface具有相同地位；可以拥有构造器、成员方法、成员变量
     *    枚举类与普通类不同之处：
     *    a）默认继承了java.lang.Enum类，所以不能继承其它父类。
     *    b）使用enum定义，默认使用final修饰，因此不能派生子类
     *    c）构造器默认使用private修饰，且只能使用private修饰
     *    d）枚举类所有实现必须在第一行给出，默认添加public static final修饰，否则无法产生实例
     *
     * 3）枚举本质上是通过普通的类来实现的，只是编译器为我们进行了处理。每个枚举类型都继承自java.lang.Enum，并自动添加了values和valueOf方法。
     *
     * 4）可使用javap来查看字节码文件，可使用javap --help查看指令
     *
     * 参考链接：
     * a）https://juejin.cn/post/6986825010597855245 Java枚举的深入理解以及实现原理
     * b）https://www.cnblogs.com/frankiedyz/p/15851123.html 字节码层面深入分析Java枚举类
     *   （文章中有参考意义的建议：研究一个问题，最好是从现象出发去看本质，先知道有哪些现象，再看看它们的本质原因是什么。）
     */

    enum Spiciness {
        NOT, MILD, MEDIUM, HOT, FLAMING //枚举的序号是从0开始的
    }

    Spiciness degree;

    public Burrito(Spiciness degree) {
        this.degree = degree;
    }

    public String toString() {
        return "Burrito is " + degree;
    }

    public static void main(String[] args) {
        System.out.println(new Burrito(Spiciness.NOT));
        System.out.println(new Burrito(Spiciness.MEDIUM));
        System.out.println(new Burrito(Spiciness.HOT));

        /**
         * 输出结果：
         * Burrito is NOT
         * Burrito is MEDIUM
         * Burrito is HOT
         *
         * 结果分析：
         * 1）所有的枚举类都默认实现了java.lang.Enum，此处打印枚举对象时，会进入Enum的toString()方法，会输出枚举名称
         */
    }

    /**
     * 使用javap查看编译后的字节码文件（通过javap即可清晰看清Enum类的特性）
     *
     * 如：javap /Users/shengyong.chen/self_remote/practice/practice-jc/target/classes/thinking/enum_type/case1/Spiciness.class
     *
     * 生成的枚举类为：
     * public final class thinking.enum_type.case1.Spiciness extends java.lang.Enum<thinking.enum_type.case1.Spiciness> {
     *   public static final thinking.enum_type.case1.Spiciness NOT;
     *   public static final thinking.enum_type.case1.Spiciness MILD;
     *   public static final thinking.enum_type.case1.Spiciness MEDIUM;
     *   public static final thinking.enum_type.case1.Spiciness HOT;
     *   public static final thinking.enum_type.case1.Spiciness FLAMING;
     *   public static thinking.enum_type.case1.Spiciness[] values();
     *   public static thinking.enum_type.case1.Spiciness valueOf(java.lang.String);
     *   static {};
     * }
     *
     */

}
