package thinking.io_relative;

import java.nio.*;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * @author orange
 * @date 2024/6/7
 */
public class ViewBuffers {

    /**
     * 知识点（18.10.3）: 视图缓冲器
     */
    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.wrap(
                new byte[] { 0, 0, 0, 0, 0, 0, 0, 'a'});
        bb.rewind();
        printnb("Byte Buffer ");
        while (bb.hasRemaining()) {
            printnb(bb.position() + " -> " + bb.get() + "，");
        }
        print();

        CharBuffer cb =
                ((ByteBuffer)bb.rewind()).asCharBuffer();
        printnb("Char Buffer");
        while (cb.hasRemaining()) {
            printnb(bb.position() + " -> " + bb.get() + "，");
        }
        print();

        FloatBuffer fb =
                ((ByteBuffer)bb.rewind()).asFloatBuffer();
        printnb("Float Buffer");
        while (fb.hasRemaining()) {
            printnb(fb.position() + " -> " + fb.get() + "，");
        }
        print();

        IntBuffer ib =
                ((ByteBuffer)bb.rewind()).asIntBuffer();
        printnb("Int Buffer");
        while (ib.hasRemaining()) {
            printnb(ib.position() + " -> " + ib.get() + "，");
        }
        print();

        LongBuffer lb =
                ((ByteBuffer)bb.rewind()).asLongBuffer();
        printnb("Long Buffer");
        while (lb.hasRemaining()) {
            printnb(lb.position() + " -> " + lb.get() + "，");
        }
        print();

        ShortBuffer sb =
                ((ByteBuffer)bb.rewind()).asShortBuffer();
        printnb("Short Buffer");
        while (sb.hasRemaining()) {
            printnb(sb.position() + " -> " + sb.get() + "，");
        }
        print();

        DoubleBuffer db =
                ((ByteBuffer)bb.rewind()).asDoubleBuffer();
        printnb("Double Buffer");
        while (db.hasRemaining()) {
            printnb(db.position() + " -> " + db.get() + "，");
        }
        print();
    }
}
