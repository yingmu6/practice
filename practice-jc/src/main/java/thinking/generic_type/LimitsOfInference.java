package thinking.generic_type;

/**
 * @author chensy
 * @date 2024/5/18
 */
public class LimitsOfInference {

    /**
     * 知识点（15.4.1）：类型参数推断
     */
    // static void f(Map<Person, List<? extends Pet>> petPeople) {}

    public static void main(String[] args) {
       // f(New.map())  //会编译错误
    }
}
