package relative.basic.bit_operation;

import java.math.BigInteger;
import java.text.DecimalFormat;

/**
 * 位运算
 *
 * @author : chensy
 * Date : 2020/10/20 上午12:07
 */
public class BasicTest {
    public static void main(String[] args) {
//        basic();
//        operation();
//        print();
        //format();
//        printf();
        byte[] bytes = int2Bytes(-669987);
        System.out.println(bytes2Int(bytes));
    }

    // 将int 转换为 byte数组
    public static byte[] int2Bytes(int i) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (i >> 24);
        bytes[1] = (byte) (i >> 16);
        bytes[2] = (byte) (i >> 8);
        bytes[3] = (byte) i;
        return bytes;
    }

    // 将byte数组转换为int
    public static int bytes2Int(byte[] bytes) {
        return bytes[3] & 0xff
                | (bytes[2] & 0xff) << 8
                | (bytes[1] & 0xff) << 16
                | (bytes[0] & 0xff) << 24;
    }

    private static void basic() {
        System.out.println("与运算：" + (0 & 1) + ";" + (1 & 1) + ";" + (1 & 0) + ";" + (0 & 0));
        System.out.println("或运算：" + (0 | 1) + ";" + (1 | 1) + ";" + (1 | 0) + ";" + (0 | 0));
        System.out.println("异或运算：" + (0 ^ 1) + ";" + (1 ^ 1) + ";" + (1 ^ 0) + ";" + (0 ^ 0));
        System.out.println("取反运算：" + (~0) + ";" + (~1) + ";" + (~-1)); //为啥此处显示 -1，-2 ，解：将数字转换为补码，然后再进行运算

        System.out.println("左移运算：" + (3 << 5)); //每左移一位，相当于该数乘以2（3*2^n）
        System.out.println("右移运算：" + (4 >> 5)); //操作数每右移一位，相当于该数除以2。

        // 有符号右移，
        System.out.println("符号移动：" + (-23 >>> 2) + ";" + (-23 >> 2));
        System.out.println("符号移动：" + (-9 >>> 2) + ";" + (-9 >> 2));

    }

    protected static final byte FLAG_REQUEST = (byte) 0x80; // 十进制为128，二进制位10000000

    private static void operation() {
        byte HESSIAN2_SERIALIZATION_ID = 2; //00000010
        byte JAVA_SERIALIZATION_ID = 3;     //00000011
        byte COMPACTED_JAVA_SERIALIZATION_ID = 4; //00000100
        System.out.println(FLAG_REQUEST);

        System.out.println((FLAG_REQUEST | HESSIAN2_SERIALIZATION_ID) + "，" + (byte) (FLAG_REQUEST | HESSIAN2_SERIALIZATION_ID));
//        System.out.println((byte) (FLAG_REQUEST | JAVA_SERIALIZATION_ID));
//        System.out.println((byte) (FLAG_REQUEST | COMPACTED_JAVA_SERIALIZATION_ID));

        System.out.println(FLAG_REQUEST | HESSIAN2_SERIALIZATION_ID);
        System.out.println((byte) 130);

        //byte 是一个字节，共有 2^8=256 种可能性，也就是 -128~127。
        // 进行byte强转时，若超过127，会从-128开始处理
        // https://cloud.tencent.com/developer/article/1499063
    }

    // 输出各种进制的数（类型强转）
    private static void print() {
        int b = 128;
        byte a = (byte) b;
        System.out.println("十进制表示：" + a + "，" + Double.toHexString(a));
        System.out.println("二进制表示：" + Integer.toBinaryString(a));
        System.out.println("八进制表示：" + Integer.toOctalString(a));
        System.out.println("十六进制表示：" + Integer.toHexString(a));

        byte[] arr = {(byte) 128};
        String str = new BigInteger(1, arr).toString(2);
        System.out.println(str); //输出10000000
        System.out.println((byte) 129);
    }

    // 输出固定长度二进制
    private static void format() {
        DecimalFormat df = new DecimalFormat("00000000");
        int a = 25;
        System.out.println("十进制格式化输出:" + df.format(a));

        String binaryStr = Integer.toBinaryString(a);
        Integer num = Integer.valueOf(binaryStr);
        System.out.println("二进制未格式化输出:" + Integer.toBinaryString(a));
        System.out.println("二进制格式化输出:" + df.format(num));
    }

    // 输出八进制、十六进制，printf()方法不能输出二进制
    private static void printf() {
        int a = 25;
        System.out.printf("十进制表示，%d\n", a);
        System.out.printf("八进制表示，%o\n", a);
        // %08d解读：d表明按八进制表示，0表示：补齐的内容，8表示：位数
        System.out.printf("八进制表示，限制位数，和补齐的内容，%08d\n", a);
        System.out.printf("十六进制表示，%x\n", a);
    }
}

/**
 * https://www.runoob.com/w3cnote/bit-operation.html 位运算|菜鸟教程
 * 从现代计算机中所有的数据二进制的形式存储在设备中。即 0、1 两种状态，
 * 计算机对二进制数据进行的运算(+、-、*、/)都是叫位运算，即将符号位共同参与运算的运算(会先转换为二进制再做运算)
 * <p>
 * &    与	两个位都为1时，结果为1，其余为0（且的关系）
 * |	或	只要有一个位为1，结果为1，其余为0（或的关系）
 * ^	异或	两个位相相异，结果为1，其余为0（相异的关系）
 * ~	取反	0变1，1变0
 * <<	左移	各二进位全部左移若干位，高位丢弃，低位补0
 * >>	右移	各二进位全部右移若干位，对无符号数，高位补0，有符号数，各编译器处理方法不一样，有的补符号位（算术右移），有的补0（逻辑右移）
 * <p>
 * <p>
 * <p>
 * 异或的几条性质:
 * 1、交换律
 * 2、结合律 (a^b)^c == a^(b^c)
 * 3、对于任何数x，都有 x^x=0，x^0=x
 * 4、自反性: a^b^b=a^0=a;
 * <p>
 */
