package relative.basic.io;

import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

public class ImageBase64Test {
    public static void main(String[] args) {
        String imageBase64 = getImageBase64("D:\\self_project\\practice\\practice-jc\\src\\main\\java\\relative\\basic\\io\\test.jpg");
        int totalLen = imageBase64.getBytes().length;
        double space = new BigDecimal((float)totalLen/1024/1024).setScale(2, BigDecimal.ROUND_UP).doubleValue();
        System.out.println("字节数：" + totalLen + ", 大小：" + space + "M");
    }

    // 获取图片的base64字符串
    public static String getImageBase64(String filePath) {

        byte arr[];
        long fileLen;
        InputStream inputStream = null;
        try {
            File file = new File(filePath);
            fileLen = file.length();
            System.out.println("文件大小：" + file.length() + "字节数");
            inputStream = new FileInputStream(file);
            arr = new byte[inputStream.available()];
            inputStream.read(arr);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        BASE64Encoder encoder = new BASE64Encoder();
        String base64 = encoder.encode(arr);
        System.out.println("base64字符串相比原来的倍数：" + new BigDecimal((float)base64.length()/fileLen).setScale(2, BigDecimal.ROUND_UP));
        return base64;
    }
}
