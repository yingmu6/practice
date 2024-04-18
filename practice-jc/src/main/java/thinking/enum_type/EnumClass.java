package thinking.enum_type;

/**
 * @author chensy
 * @date 2024/4/8
 */
public class EnumClass { //@thinking Done
    enum Shrubbery {
        GROUND, CRAWLING, HANGING
    }

    /**
     * 知识点：基本enum特性
     * 1）枚举类被编译器额外添加了两个 static 方法：values() 和 valueOf(String)
     * 2）编译器为枚举类添加了 final 关键字，使得枚举类不能被其他类继承
     * 3）枚举类的构造器都被改为了 private，因此外界不可以使用枚举类创建对象
     */
    public static void main(String[] args) {
        for (Shrubbery s: Shrubbery.values()) { //values()列举出所有枚举值
            System.out.println(s + " ordinal: " + s.ordinal()); //枚举的序号
            System.out.println(s.compareTo(Shrubbery.CRAWLING) + " ");
            System.out.println(s.equals(Shrubbery.CRAWLING) + " ");
            System.out.println(s == Shrubbery.CRAWLING);
            System.out.println(s.getDeclaringClass());
            System.out.println(s.name());
            System.out.println("------------------");
        }

        for (String s : "HANGING CRAWLING GROUND".split(" ")) {
            Shrubbery shrub = Enum.valueOf(Shrubbery.class, s);
            System.out.println(shrub);
        }

        /**
         * 输出结果：
         * GROUND ordinal: 0
         * -1
         * false
         * false
         * class thinking.enum_type.case1.Shrubbery
         * GROUND
         * ------------------
         * CRAWLING ordinal: 1
         * 0
         * true
         * true
         * class thinking.enum_type.case1.Shrubbery
         * CRAWLING
         * ------------------
         * HANGING ordinal: 2
         * 1
         * false
         * false
         * class thinking.enum_type.case1.Shrubbery
         * HANGING
         * ------------------
         * HANGING
         * CRAWLING
         * GROUND
         *
         * 结果分析：
         * 1）枚举中的values()方法列举出所有的枚举值，是编译器添加的方法
         * 2）枚举值可以用 == 、equals来比较是否为同一个枚举值
         * 3）ordinal()是获取枚举所在位置的序号，是从0开始的
         */
    }

    /**
     * 使用javap查看编译后的字节码文件（通过javap即可清晰看清Enum类的特性）
     *
     * 如：javap /Users/shengyong.chen/self_remote/practice/practice-jc/target/classes/thinking/enum_type/case1/Shrubbery.class
     *
     * 生成的枚举类为：
     * public final class thinking.enum_type.case1.Shrubbery extends java.lang.Enum<thinking.enum_type.case1.Shrubbery> {
     *   public static final thinking.enum_type.case1.Shrubbery GROUND;
     *   public static final thinking.enum_type.case1.Shrubbery CRAWLING;
     *   public static final thinking.enum_type.case1.Shrubbery HANGING;
     *   public static thinking.enum_type.case1.Shrubbery[] values();
     *   public static thinking.enum_type.case1.Shrubbery valueOf(java.lang.String);
     *   static {};
     * }
     *
     */
}
