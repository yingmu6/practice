package thinking.enum_type;

/**
 * @author chensy
 * @date 2024/4/8
 */
public class NonEnum { //@thinking Done

    /**
     * 知识点：获取Class类中的枚举值列表
     */

    public static void main(String[] args) {
        Class<Integer> intClass = Integer.class;

        try {
            for (Object en : intClass.getEnumConstants()) {
                System.out.println(en);
            }

//            for (Object en : Explore.class.getEnumConstants()) { //输出HERE THERE
//                System.out.println(en);
//            }
        } catch (Exception e) {
            System.out.println(e);
        }

        /**
         * 输出结果：
         * java.lang.NullPointerException
         *
         * 结果分析：
         * 1）getEnumConstants()输出Class类中的枚举常量，若没有则返回null
         *   （查看对应的源码，会判断Class是否为枚举类型，只有枚举类，getEnumConstants()才会返回枚举值列表）
         */
    }
}
