package thinking.enum_type;

/**
 * @author chensy
 * @date 2024/4/8
 */
public class UpcastEnum { //@thinking Done

    /**
     * 知识点：枚举的values()方法访问
     * 1）values()是编译器插入到enum中的static方法，将enum实例向上转型为Enum，values()方法是不可访问的
     *
     * 2）通过Class对象的getEnumConstants()可以获取到所有enum实例，内部实现就是获取values()方法的值
     */

    enum Search {
        HITHER, YON
    }

    public static void main(String[] args) {
        Search[] vals = Search.values();
        Enum e = Search.HITHER;
        // e.values(); //报类中没有values()方法报错
        for (Enum en : e.getClass().getEnumConstants()) {
            System.out.println(en);
        }

        /**
         * 输出结果：
         * HITHER
         * YON
         *
         * 结果分析：
         * 1）Enum中没有values()方法，直接通过Enum访问不了，可通过Class对象的getEnumConstants()获取enum实例
         */
    }
}
