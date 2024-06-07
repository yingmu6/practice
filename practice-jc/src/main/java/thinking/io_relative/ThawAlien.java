package thinking.io_relative;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @author orange
 * @date 2024/6/7
 */
public class ThawAlien {

    /**
     * 知识点（18.12.1）：寻找类
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(new File("..", "X.fle"))
        );
        Object mystery = in.readObject();
        System.out.println(mystery.getClass());
    }
}
