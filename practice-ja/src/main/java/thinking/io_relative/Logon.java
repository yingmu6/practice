package thinking.io_relative;

import java.io.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/7
 */
public class Logon implements Serializable {

    /**
     * 知识点（18.12.2）：transient关键字
     */
    private Date date = new Date();
    private String username;
    private transient String password;
    public Logon(String name, String pwd) {
        username = name;
        password = pwd;
    }

    public String toString() {
        return "logon info：\n  username：" + username +
                "\n date：" + date + "\n password：" + password;
    }

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Logon a = new Logon("Hulk", "myLittlePony");
        print("logon a = " + a);
        ObjectOutputStream o = new ObjectOutputStream(
                new FileOutputStream("Logon.out")
        );
        o.writeObject(a);
        o.close();
        TimeUnit.SECONDS.sleep(1);
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("Logon.out")
        );
        print("Recovering object at " + new Date());
        a = (Logon) in.readObject();
        print("Logon a = " + a);
    }
}
