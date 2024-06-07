package thinking.io_relative;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author orange
 * @date 2024/6/7
 */
public class FreezeAlien {

    /**
     * 知识点（18.12.1）：寻找类
     */
    public static void main(String[] args) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("X.file")
        );
        Alien quellek = new Alien();
        out.writeObject(quellek);
    }
}
