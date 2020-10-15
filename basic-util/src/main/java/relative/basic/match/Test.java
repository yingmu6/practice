package relative.basic.match;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 数学运算
 * @author : chensy
 * Date : 2020/10/15 上午9:24
 */
public class Test {
    public static void main(String[] args) throws Exception{
//        basic();
        streamDeal();
    }

    private static void streamDeal() throws Exception{
        File file = new File("a667.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        OutputStream out = new FileOutputStream(file);
        byte[] arr = new byte[3];
        arr[0] = 5;
        arr[1] = 6;
        arr[2] = 7;
//        out.write(arr, 0, arr.length);
        out.write(arr);
        out.flush(); //todo @csy 为啥没写道输出流中？
        System.out.println(arr);
        out.close();
    }

    private static void basic() {
        byte OBJECT = (byte) 0x80;
        System.out.println(OBJECT);
        System.out.println(OBJECT | 20);

        Map<Object, Integer> mRefs = new HashMap<>();
        Animal animal1 = new Animal();
        animal1.setAge(1);
        Animal animal2 = new Animal();
        animal2.setAge(2);
        mRefs.put(animal1, mRefs.size()); //当设置了对象后，map也对应改变了
        mRefs.put(animal2, mRefs.size());
        System.out.println(mRefs.size());

        System.out.println(0 | 51);
        int a = 1;
        System.out.println(a++ + "," + a);
    }
}
class Animal {
    private int age;
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
