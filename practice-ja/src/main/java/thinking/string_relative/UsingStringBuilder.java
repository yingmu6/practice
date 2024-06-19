package thinking.string_relative;

import net.mindview.util.RandomGenerator;

import java.util.Random;

/**
 * @author orange
 * @date 2024/6/4
 */
public class UsingStringBuilder {

    /**
     * 知识点（13.2）：StringBuilder
     */
    public static Random rand = new Random(47);
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < 25; i++) {
           result.append(rand.nextInt(100));
           result.append("，");
        }
        result.delete(result.length() - 2, result.length());
        result.append("]");
        return result.toString();
    }

    public static void main(String[] args) {
        UsingStringBuilder usb = new UsingStringBuilder();
        System.out.println(usb);
    }
}
