package thinking.type_info;

/**
 * @author orange
 * @date 2024/6/12
 */
public class GenericClassReference {

    /**
     * 知识点（14.2.2）：泛化的Class引用
     */
    public static void main(String[] args) {
        Class intClass = int.class;
        Class<Integer> genericIntClass = int.class;
        genericIntClass = Integer.class;
        intClass = double.class;
        // genericIntClass = double.class; //Illegal
    }
}
