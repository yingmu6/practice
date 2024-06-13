package thinking.type_info.toys;

/**
 * @author orange
 * @date 2024/6/12
 */
public class GenericToyTest {

    /**
     * 知识点（14.2.2）：泛化的Class引用
     */
    public static void main(String[] args) throws Exception {
        Class<FancyToy> ftClass = FancyToy.class;
        FancyToy fancyToy = ftClass.newInstance();
        Class<? super FancyToy> up = ftClass.getSuperclass();
        Object obj = up.newInstance();
    }
}
