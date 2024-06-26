package thinking.string_relative;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * @author orange
 * @date 2024/6/4
 */
public class SimpleRead {

    /**
     * 知识点（13.7）：扫描输入
     */
    public static BufferedReader input = new BufferedReader(
            new StringReader("Sir Robin of Camelot\n22 1.61803"));

    public static void main(String[] args) {
        try {
            System.out.println("What is your name?");
            String name = input.readLine();
            System.out.println(name);
            System.out.println(
                    "How old are you? What is your favorite double?");
            System.out.println("(input：<age> <double>)");
            String numbers = input.readLine();
            System.out.println(numbers);
            String[] numArray = numbers.split(" ");
            int age = Integer.parseInt(numArray[0]);
            double favorite = Double.parseDouble(numArray[1]);
            System.out.format("Hi %s.\n", name);
            System.out.format("In 5 years you will be %d.\n", age + 5);
            System.out.format("My favorite double is %f.", favorite / 2);
        } catch (IOException e) {
            System.err.println("I/O exception");
        }
    }
}
