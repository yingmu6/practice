package thinking.io_relative;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * @author orange
 * @date 2024/6/7
 */
public class RecoverCADState {

    /**
     * 知识点（18.12.3）：使用"持久性"
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("CADState.out")
        );
        List<Class<? extends StoreCADState.Shape>> shapeTypes =
                (List<Class<? extends StoreCADState.Shape>>)in.readObject();
        StoreCADState.Line.deserializeStaticState(in);
        List<StoreCADState.Shape> shapes = (List<StoreCADState.Shape>) in.readObject();
        System.out.println(shapes);
    }
}
