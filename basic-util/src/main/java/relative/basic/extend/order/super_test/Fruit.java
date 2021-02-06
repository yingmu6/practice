package relative.basic.extend.order.super_test;

import lombok.Data;

/**
 * @author : chensy
 * Date : 2020/11/20 上午6:54
 */
@Data
public class Fruit {
    private double weight;

    public Fruit(double weight) {
        this.weight = weight;
    }
}
