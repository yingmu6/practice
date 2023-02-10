package basic.unit.zxing;

import com.google.zxing.EncodeHintType;
import com.google.zxing.oned.Code128Writer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

/**
 * 条形码工具，内有生成条形码，与解析办法
 *
 */
public class ZxingBarCodeUtil {
    /**
     * 条形码编码
     *
     * @param contents
     * @return
     */
    public static BufferedImage encode(String contents) {

        //配置条码参数
        Map<EncodeHintType,Object> hints = new HashMap<>();
        //设置条码两边空白边距为0，默认为10，如果宽度不是条码自动生成宽度的倍数则MARGIN无效
        hints.put(EncodeHintType.MARGIN, 15);

        //为了无边距，需设置宽度为条码自动生成规则的宽度
        int width = new Code128Writer().encode(contents).length;
        //前端可控制高度，不影响识别
        int height = 32;
        //条码放大倍数
        int codeMultiples = 1;
        //获取条码内容的宽，不含两边距，当EncodeHintType.MARGIN为0时即为条码宽度
        int codeWidth = width * codeMultiples;

        /* ZXing 条码边距及总宽度-默认计算规则
        codeWidth: 自定义的条码宽度
        fullWidth: 条码根据编码内容自动生成编码数组长度(new Code128Writer().encode(contents).length)+边距MARGIN
        outputWidth: codeWidth 与 fullWidth 的最大值
        //放大倍数(取整)
        int multiple = outputWidth / fullWidth;
        //边距
        int leftPadding = (outputWidth - (inputWidth * multiple)) / 2;
        生成条码长度为: outputWidth + 2 * leftPadding
         */

        try {
            // 图像数据转换，使用了矩阵转换 参数顺序分别为：编码内容，编码类型，生成图片宽度，生成图片高度，设置参数
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                    BarcodeFormat.CODE_128, codeWidth, height, hints);
//            MatrixToImageWriter.writeToStream(bitMatrix, "png", new FileOutputStream("d:/code39.png"));
            return  MatrixToImageWriter.toBufferedImage(bitMatrix);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析条形码
     *
     * @param imgPath
     * @return
     */
    public static String decode(String imgPath) {
        BufferedImage image = null;
        Result result = null;
        try {
            image = ImageIO.read(new File(imgPath));
            if (image == null) {
                throw new RuntimeException("the decode image may be not exists.");
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            result = new MultiFormatReader().decode(bitmap, null);
            return result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        try {

            String imgcode = "CGRKD-20221212-2664";

            BufferedImage buffImg = ZxingBarCodeUtil.encode(imgcode);

            String filePath = "D:\\self_project\\practice\\practice-gz\\src\\main\\java\\basic\\unit\\zxing\\zxing-test.jpeg";
            File file = new File(filePath);
            OutputStream ops = new FileOutputStream(file);
            ImageIO.write(buffImg, "jpeg", ops);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}