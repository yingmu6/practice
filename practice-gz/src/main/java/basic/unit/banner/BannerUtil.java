package basic.unit.banner;

import org.krysalis.barcode4j.HumanReadablePlacement;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 条形码测试工具
 * @Author chenSy
 * @Date 2023/01/12 10:52
 * @Description
 */
public class BannerUtil {

    /**
     * 生成条形码文件
     *
     * @param msg  条形码的文本内容
     * @param path 生成条形码的文件路径
     * @return
     */
    public static File generateFile(String msg, String path) {
        File file = new File(path);
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            generateBarCode128(msg, 9.2, 0.18, true, false, outputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    /**
     * 生成code128条形码
     *
     * @param message       要生成的文本
     * @param height        条形码的高度
     * @param width         条形码的宽度
     * @param withQuietZone 是否两边留白
     * @param hideText      隐藏可读文本
     * @param outputStream  输出流
     */
    public static void generateBarCode128(String message, Double height, Double width, boolean withQuietZone, boolean hideText, OutputStream outputStream) {
        Code128Bean bean = new Code128Bean();

        // 分辨率，越大条形码就越大
        int dpi = 180;

        // 设置两侧是否留白
        bean.doQuietZone(withQuietZone);

        // 设置条形码高度和宽度
        bean.setBarHeight(height);
        if (width != null) {
            bean.setModuleWidth(width);
        }
        // 设置文本位置（包括是否显示）
        if (hideText) {
            bean.setMsgPosition(HumanReadablePlacement.HRP_NONE);
        }
        // 设置图片类型
        String format = "image/png";
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(outputStream, format, dpi,
                BufferedImage.TYPE_BYTE_BINARY, false, 0);

        /* 生产条形码 */
        bean.generateBarcode(canvas, message);
        try {
            canvas.finish();
        } catch (IOException e) {
            //ByteArrayOutputStream won't happen
        }
    }

    public static void main(String[] args) {
        String msg = "CGRKD-20221212-2444";
        //生成条形码路径
        String path = "D:\\self_project\\practice\\practice-gz\\src\\main\\java\\basic\\unit\\banner\\barcode3_180-9.2-0.18.png";
        generateFile(msg, path);
    }

    /**
     * 分辨率测试验证结果
     * 1）dpi = 120时，扫不出来
     * 2）dpi = 150时，能扫出来，但是会有概率出现非正常的字符，如"C+RK%2F-2008"，概率还比较大
     * 3）dpi = 180时，能扫出来，测试了5次都是正确的
     * 4）dpi = 210时，能扫出来，测试了5次都是正确的
     *
     */
}
