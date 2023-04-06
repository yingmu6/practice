package relative.basic.bigdecimal;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @Author chenSy
 * @Date 2023/04/06 16:09
 * @Description
 */
public class BigDecimalTest {


    /**
     * 场景1：基本使用
     */
    @Test
    public void test_basicUse() {
        int a = 3;
        BigDecimal bigDecimal = new BigDecimal(a);
        bigDecimal.setScale(3);
        System.out.println(bigDecimal);
    }

    /**
     * 场景2：四舍五入
     */

    /**
     * 场景3：精度使用
     */
}
