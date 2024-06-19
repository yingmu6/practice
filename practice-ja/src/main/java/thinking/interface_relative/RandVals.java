package thinking.interface_relative;

import java.util.Random;

/**
 * @author orange
 * @date 2024/6/5
 */
public interface RandVals {

    Random RAND = new Random(47);
    int RANDOM_INT = RAND.nextInt(10);
    long RANDOM_LONG = RAND.nextLong() * 10;
    float RANDOM_FLOAT = RAND.nextLong() * 10;
    double RANDOM_DOUBLE = RAND.nextDouble() * 10;
}