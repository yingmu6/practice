package relative.javassist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : chensy
 * Date : 2020/11/12 上午9:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fruit {
    private double price;
    private double weight;
    public int buyNum() {
        return (int) (this.price / this.weight);
    }
    public void showInfo() {
        System.out.println("Fruit Buy !!!");
    }
}
