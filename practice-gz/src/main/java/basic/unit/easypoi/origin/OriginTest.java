package basic.unit.easypoi.origin;

import cn.afterturn.easypoi.entity.ImageEntity;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 对easypoi官方测试用例验证
 * @Author chenSy
 * @Date 2023/01/17 16:03
 * @Description
 */
public class OriginTest {
    public static void main(String[] args) throws Exception{
        img03();
    }

    public static void img03() throws Exception {
        TemplateExportParams params = new TemplateExportParams(
                "D:\\self_project\\practice\\practice-gz\\src\\main\\java\\basic\\unit\\easypoi\\origin\\exportTemp_image.xls", true);
        Map<String, Object> map = new HashMap<String, Object>();
        // sheet 2
        map.put("month", 10);
        Map<String, Object> temp;
        for (int i = 1; i < 8; i++) {
            temp = new HashMap<String, Object>();
            temp.put("per", i * 10);
            temp.put("mon", i * 1000);
            temp.put("summon", i * 10000);
            ImageEntity image = new ImageEntity();
//            image.setHeight(400);
//            image.setWidth(500);
            image.setRowspan(2);
            image.setColspan(2);
            image.setUrl("D:\\self_project\\practice\\practice-gz\\src\\main\\java\\basic\\unit\\easypoi\\origin\\barcode.jpg");
            temp.put("image", image);
            map.put("i" + i, temp);
        }
        Workbook book = ExcelExportUtil.exportExcel(params, map);
        FileOutputStream fos = new FileOutputStream("D:\\self_project\\practice\\practice-gz\\src\\main\\java\\basic\\unit\\easypoi\\origin\\exportTemp_image_new6.xls");
        book.write(fos);
        fos.close();

    }

}
