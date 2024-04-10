package thinking.enum_type.case2;

/**
 * @author chensy
 * @date 2024/4/8
 */
public class NonEnum {
    public static void main(String[] args) {
        Class<Integer> intClass = Integer.class;

        try {
            for (Object en : intClass.getEnumConstants()) {
                System.out.println(en);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
