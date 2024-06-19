package thinking.io_relative;

import java.io.*;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/7
 */
public class Blips {

    /**
     * 知识点（18.12.2）：序列化的控制
     */

    static class Blip1 implements Externalizable {
        public Blip1() {
            print("Blip1 Constructor");
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            print("Blip1.writeExternal");
        }

        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            print("Blip1.readExternal");
        }
    }

    static class Blip2 implements Externalizable {
        Blip2() {
            print("Blip2 Constructor");
        }
        public void writeExternal(ObjectOutput out) throws IOException {
           print("Blip2.writeExternal");
        }

        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            print("Blip2.readExternal");
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        print("Constructing Objects：");
        Blip1 b1 = new Blip1();
        Blip2 b2 = new Blip2();
        ObjectOutputStream o = new ObjectOutputStream(
                new FileOutputStream("Blips.out")
        );
        print("Saving objects:");
        o.writeObject(b1);
        o.writeObject(b2);
        o.close();

        ObjectInputStream in  = new ObjectInputStream(
                new FileInputStream("Blips.out")
        );
        print("Recovering b1:");
        b1 = (Blip1) in.readObject();
    }
}
