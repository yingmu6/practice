package thinking.type_info;

/**
 * @author orange
 * @date 2024/6/12
 */
public class WildcardClassReferences {

    /**
     * 知识点（14.2.2）：通配符?
     */
    public static void main(String[] args) {
        Class<?> intClass = int.class;
        intClass = double.class;
    }
}
