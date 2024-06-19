package relative.basic.enum_test;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @Author chenSy
 * @Date 2022/12/06 10:48
 * @Description
 */
public class ListEnumTest {
    public static void main(String[] args) {

        ListEnumTest listEnumTest = new ListEnumTest();
        listEnumTest.testEnum();
    }

    private void testEnum () {
        ListEnumTest.FruitEnum fruitEnum =  FruitEnum.getFruitByColor("红色");
        System.out.println(fruitEnum);
    }

    enum FruitEnum {
        APPLE("苹果", Lists.newArrayList("红色", "褐色")),
        BANANA("香蕉", Lists.newArrayList("黄色", "绿色"));

        private String name;
        private List<String> colors;

        public String getName() {
            return name;
        }

        public List<String> getColors() {
            return colors;
        }

        FruitEnum(String name, List<String> colors) {
            this.name = name;
            this.colors = colors;
        }

        static public FruitEnum getFruitByColor(String color) { //根据条件值查枚举值
            if (StringUtils.isEmpty(color)) {
                return null;
            }
            for (FruitEnum fruitEnum : FruitEnum.values()) {
                List<String> colors = fruitEnum.getColors();
                if (colors.contains(color)) {
                    return fruitEnum;
                }
            }
            return null;
        }
    }
}
