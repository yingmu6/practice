package thinking.io_relative;

import java.io.*;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/7
 */
public class Blip3 implements Externalizable {

    /**
     * 知识点（18.12.2）：序列化的控制
     */

    private int i;
    private String s;
    public Blip3() {
        print("Blip3 Constructor");
    }

    public Blip3(String x, int a) {
        print("Blip3(String x, int a)");
        s = x;
        i = a;
    }

    public String toString() { return s + 1; }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        print("Blip3.writeExternal");
        out.writeObject(s);
        out.writeInt(i);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        print("Blip3.readExternal");
        s = (String) in.readObject();
        i = in.readInt();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        print("Constructing objects：");
        Blip3 b3 = new Blip3("A string ", 47);
        print(b3);
        ObjectOutputStream o = new ObjectOutputStream(
                new FileOutputStream("Blip3.out")
        );
        print("Saving object：");
        o.writeObject(b3);
        o.close();
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("Blip3.out")
        );
        print("Recovering b3:");
        b3 = (Blip3) in.readObject();
        print(b3);
    }

}
