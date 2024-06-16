package interview.offer_come.data_struct;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class BitMap {

    private byte[] bytes;

    private int length;

    public BitMap(int length) {
        this.length = length;
        bytes = new byte[length % 8 == 0 ? length / 8 : length / 8 + 1];
    }

    public boolean get(int index) {
        int i = index & 7;
        if ((bytes[index >> 3] & (01111111 >>> (7-i))) >> i == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void set(int index, boolean value) {
        if (value) {
            bytes[index >> 3] |= 1 << (index & 7);
        } else {
            bytes[index >> 3] &= ~(1 << (index & 7));
        }
    }
}
