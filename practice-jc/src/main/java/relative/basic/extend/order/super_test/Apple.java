package relative.basic.extend.order.super_test;

import lombok.Data;

/**
 * @author : chensy
 * Date : 2020/11/20 上午6:54
 */
@Data
public class Apple extends Fruit{
    private String name;

    public Apple() {
        super(0);
    }
}
