package thinking.collection_deep.case1;

import java.util.Random;

/**
 * @author chensy
 * @date 2024/4/8
 */
public class Prediction {

    private static Random rand = new Random(47);

    private boolean shadow = rand.nextDouble() > 0.5;

    public String toString() {
        if (shadow) {
            return "Six more weeks of Winter!";
        } else {
            return "Early Spring!";
        }
    }
}
