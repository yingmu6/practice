package thinking.type_info;

/**
 * @author orange
 * @date 2024/6/12
 */
public class BoundedClassReferences { //@TkY-Done

    /**
     * 知识点（14.2.2）：限定某种类型
     *
     * 知识点概括：
     * 1）通过设置泛型的上界或下界，来限制泛型的范围
     */
    public static void main(String[] args) { //Done
        Class<? extends Number> bounded = int.class;
        bounded = double.class;
        bounded = Number.class;

        // bounded = BoundedClassReferences.class; //此处赋值就会报类型不匹配的语法错误

        /**
         * 结果分析：
         * 1）bounded引用指定了类型的上界为Number，即指定了泛型的范围
         */
    }
}
