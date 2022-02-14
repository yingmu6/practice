package relative.basic.bit_operation;

import lombok.Getter;
import lombok.Setter;

/**
 * 32盏灯开关处理
 *
 * @author chensy
 * @date 2021/11/2
 */
@Setter
@Getter
public class BitTest {
    int switchStatus; //一个int占用4个字节，刚好32位

    public static void main(String[] args) {
        BitTest bitTest = new BitTest();
        bitTest.turnOn(6);
        System.out.println(bitTest.getSwitchStatus());
    }

    private void turnOn(int i) {
        this.switchStatus |= (1 << i - 1);
    }

}


