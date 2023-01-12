package basic.unit.easypoi.template;

import basic.unit.banner.BannerUtil;
import cn.afterturn.easypoi.entity.ImageEntity;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EasyPoi工具对应测试
 *
 * @Author chenSy
 * @Date 2023/01/11 20:35
 * @Description
 */
public class Test {
    public static void main(String[] args) throws Exception {
        useTemplate();
//        basic();
    }

    public static void useTemplate() throws Exception {
        TemplateExportParams params = new TemplateExportParams(
                "D:\\self_project\\practice\\practice-gz\\src\\main\\java\\basic\\unit\\easypoi\\tmp\\apply_from.xls");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("date", "2014-12-25");
        map.put("money", 2000000.00);
        map.put("upperMoney", "贰佰万");
        map.put("company", "执笔潜行科技有限公司");
        map.put("bureau", "财政局333");
        map.put("person", "JueYue");
        map.put("phone", "1879740****");
        map.put("financialCode", "31EY7#RT");

        // 设置条形码图片
        ImageEntity image = new ImageEntity();
        image.setHeight(200);
        image.setWidth(500);
        image.setUrl("D:\\self_project\\practice\\practice-gz\\src\\main\\java\\basic\\unit\\easypoi\\annotation\\a.png");
        map.put("barImage", image);

        List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
        for (int i = 0; i < 4; i++) {
            Map<String, String> lm = new HashMap<String, String>();
            lm.put("id", i + 1 + "");
            lm.put("zijin", i * 10000 + "");
            lm.put("bianma", "A001");
            lm.put("mingcheng", "设计");
            lm.put("xiangmumingcheng", "EasyPoi " + i + "期");
            lm.put("quancheng", "项目");
            lm.put("sqje", i * 10000 + "");
            lm.put("hdje", i * 10000 + "");

            listMap.add(lm);
        }
        map.put("maplist", listMap);

        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
//        File savefile = new File("D:/home/excel/");
//        if (!savefile.exists()) {
//            savefile.mkdirs();
//        }
        FileOutputStream fos = new FileOutputStream("D:\\self_project\\practice\\practice-gz\\src\\main\\java\\basic\\unit\\easypoi\\tmp\\apply_from_new.xls");
        workbook.write(fos);
        fos.flush();
        fos.close();
    }

    private static void basic() throws Exception {

        // 本地Excel文件路径
        String fileName = "D:\\self_project\\practice\\practice-gz\\src\\main\\java\\basic\\unit\\easypoi\\tmp\\template.xls";

        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("文件不存在");
            return;
        }

        //生成模板
        TemplateExportParams templateExport = new TemplateExportParams(fileName);

        templateExport.getSheetNum();

        String billNO = "LYCKD-20221109-0366";
        //处理业务数据
        Map<String, Object> mapData = new HashMap<>();
        mapData.put("billNo", billNO);
        mapData.put("remark", "出库单的条形码信息");

        String fileUrl = "D:\\self_project\\practice\\practice-gz\\src\\main\\java\\basic\\unit\\easypoi\\tmp\\barCode.jpg";
        // 构建条形码
        BannerUtil.generateFile(billNO, fileUrl);

        // 设置条形码图片
        ImageEntity image = new ImageEntity();
        image.setHeight(200);
        image.setWidth(500);
        image.setUrl(fileUrl);
        mapData.put("barImage", image);

        Workbook workbook = ExcelExportUtil.exportExcel(templateExport, mapData);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        bos.flush();

        FileOutputStream fos = new FileOutputStream("D:\\self_project\\practice\\practice-gz\\src\\main\\java\\basic\\unit\\easypoi\\tmp\\template_new.xls");
        workbook.write(fos);
        fos.flush();
        fos.close();

//        String newFileName = "D:\\self_project\\practice\\practice-gz\\src\\main\\java\\basic\\unit\\easypoi\\tmp\\pic.xls";
//
//        // 将输出流数据写到文件中
//        File file2 = new File(newFileName);
//        OutputStream outputStream = new FileOutputStream(file2);
//        outputStream.write(bos.toByteArray());
//        outputStream.flush();

        // 将Excel转换为PDF
//        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
//        byte[] bytes = ExcelToPdf.excel2pdf(bis);
//        String content = new String(bytes);
//        System.out.println("输出的内容=" + content);
    }
}
