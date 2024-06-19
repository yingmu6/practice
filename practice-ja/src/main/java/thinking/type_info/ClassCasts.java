package thinking.type_info;

/**
 * @author orange
 * @date 2024/6/12
 */

class Building {}
class House extends Building {}

public class ClassCasts {

    /**
     * 知识点（14.2.3）：新的转型语法
     */
    public static void main(String[] args) {
        Building b = new House();
        Class<House> houseType = House.class;
        House h = houseType.cast(b);
        h = (House) b;
    }
}
