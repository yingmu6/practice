package basic.unit.easypoi;

import com.aspose.cells.PdfSaveOptions;
import com.aspose.cells.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @Author chenSy
 * @Date 2023/01/11 20:47
 * @Description
 */
public class ExcelToPdfUtil {
    private static final Logger logger = LoggerFactory.getLogger(Class.class);
    public static byte[] excel2pdf(InputStream inputStream) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfSaveOptions saveOptions = new PdfSaveOptions();
        try {
            Workbook wb = new Workbook(inputStream);
            wb.save(baos,saveOptions);
            return baos.toByteArray();
        } catch (Exception e) {
            logger.error("打印异常"+e);
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (Exception e) {
                logger.error("打印输入流关闭异常"+e);
                e.printStackTrace();
            }
        }
        return null;
    }
}
