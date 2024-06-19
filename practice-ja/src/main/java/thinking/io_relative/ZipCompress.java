package thinking.io_relative;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * @author orange
 * @date 2024/6/7
 */
public class ZipCompress {

    /**
     * 知识点（18.11.2）：用Zip进行多文件保存
     */
    public static void main(String[] args) throws IOException {
        FileOutputStream f = new FileOutputStream("test.zip");
        CheckedOutputStream csum =
                new CheckedOutputStream(f, new Adler32());
        ZipOutputStream zos = new ZipOutputStream(csum);
        BufferedOutputStream out =
                new BufferedOutputStream(zos);
        zos.setComment("A test of Java Zipping");
        for (String arg : args) {
            print("Writing file " + arg);
            BufferedReader in =
                    new BufferedReader(new FileReader(arg));
            zos.putNextEntry(new ZipEntry(arg));
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
            in.close();
            out.flush();
        }
        out.close();
        print("Checksum：" + csum.getChecksum().getValue());
        print("Reading file");
        FileInputStream fi = new FileInputStream("test.zip");
        CheckedInputStream csumi =
                new CheckedInputStream(fi, new Adler32());
        ZipInputStream in2 = new ZipInputStream(csumi);
        BufferedInputStream bis = new BufferedInputStream(in2);
        ZipEntry ze;
        while ((ze = in2.getNextEntry()) != null) {
            print("Reading file " + ze);
            int x;
            while ((x = bis.read()) != -1) {
                System.out.write(x);
            }
        }

        if (args.length == 1) {
            print("Checksum：" + csumi.getChecksum().getValue());
        }
        bis.close();
        ZipFile zf = new ZipFile("test.zip");
        Enumeration e = zf.entries();
        while (e.hasMoreElements()) {
            ZipEntry ze2 = (ZipEntry) e.nextElement();
            print("File：" + ze2);
        }
    }
}
