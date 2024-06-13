package thinking.type_info;

/**
 * @author orange
 * @date 2024/6/12
 */
public class BoundedClassReferences {

    /**
     * 知识点（14.2.2）：限定某种类型
     */
    public static void main(String[] args) {
        Class<? extends Number> bounded = int.class;
        bounded = double.class;
        bounded = Number.class;
    }
}
