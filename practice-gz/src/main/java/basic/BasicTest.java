package basic;

import lombok.Data;

import java.util.Objects;

/**
 * @author chensy
 * @date 2022/6/6
 */
public class BasicTest {
    public static void main(String[] args) {
        Animail animanl = new Animail();
        animanl.setIsAge(true);
        Boolean check = Objects.isNull(animanl.getIsAge()) || animanl.getIsAge();
        if (check) {
            System.out.println(check);
        }
    }
}

@Data
class Animail {
    private Boolean isAge;
}
