package basic.unit.easypoi.template;

import basic.unit.banner.BannerUtil;
import cn.afterturn.easypoi.entity.ImageEntity;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
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
public class TemplateTest {
    public static void main(String[] args) throws Exception {
//        useTemplate();
//        basic();
          billUse();
    }

    public static void useTemplate() throws Exception {
        TemplateExportParams params = new TemplateExportParams(
                "D:\\self_project\\practice\\practice-gz\\src\\main\\java\\basic\\unit\\easypoi\\tmp\\apply_from.xls");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("date", "2014-12-25");
        map.put("money", 2000000.00);
        map.put("upperMoney", "xxx万");
        map.put("company", "xxxx科技有限公司");
        map.put("bureau", "xxx局333");
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
        mapData.put("remark", "xxxxx");

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

    public static void billUse() throws Exception{
        Map<String, Object> mapData = new HashMap<>();
        String checkName = "xxx";
        String saveName = "";
        BigDecimal totalNum = new BigDecimal(0.600).setScale(2, BigDecimal.ROUND_UP);
        String supplierName = "xxxx";
        String billDate = "2022-12-12";
        String enterpriseName = "xxx";
        String billNo = "XXXX-20221212-2444";
        String remark = "";
        mapData.put("checkName", checkName);
        mapData.put("saveName", saveName);
        mapData.put("totalNum", totalNum);
        mapData.put("supplierName", supplierName);
        mapData.put("billDate", billDate);
        mapData.put("enterpriseName", enterpriseName);
        mapData.put("billNo", billNo);
        mapData.put("remark", remark);

        List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
        Map<String, Object> itemMap = new HashMap<String, Object>();
        itemMap.put("id","1");
        itemMap.put("skuCode","WL0106");
        itemMap.put("goodsName", "CBBBB");
        itemMap.put("commonName", "111");
        itemMap.put("articleCode", "2222");
        itemMap.put("registerCode", "XXXXX");
        itemMap.put("factoryName", "XXXXX");
        itemMap.put("spec", "333");
        itemMap.put("num", totalNum);
        itemMap.put("unitName","333");
        itemMap.put("batchBo","1");
        itemMap.put("storageConditionName", "XXXX");
        itemMap.put("productionDate", "2022-11-27");
        itemMap.put("expiryDate", "2025-10-16");
        itemMap.put("quality","XXX");
        maplist.add(itemMap);

        mapData.put("maplist", maplist);

        // 本地Excel文件路径
        String fileName = "D:\\self_project\\practice\\practice-gz\\src\\main\\java\\basic\\unit\\easypoi\\template\\origin_template_new.xls";

        //生成模板
        TemplateExportParams templateExport = new TemplateExportParams(fileName);

        String fileUrl = "D:\\self_project\\practice\\practice-gz\\src\\main\\java\\basic\\unit\\easypoi\\template\\bar.jpg";
        // 构建条形码
        BannerUtil.generateFile(billNo, fileUrl);

        // 设置条形码图片
        ImageEntity image = new ImageEntity();
//        image.setHeight(400);
//        image.setWidth(500);
//        image.setRowspan(4);
//        image.setColspan(8);

        image.setRowspan(2);
        image.setColspan(4);

        image.setUrl(fileUrl);
        mapData.put("barCodeImage", image);

        Workbook workbook = ExcelExportUtil.exportExcel(templateExport, mapData);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        bos.flush();

        FileOutputStream fos = new FileOutputStream("D:\\self_project\\practice\\practice-gz\\src\\main\\java\\basic\\unit\\easypoi\\template\\template_practice13.xls");
        workbook.write(fos);
        fos.flush();
        fos.close();
//
//        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
//        byte[] bytes = ExcelToPdfUtil.excel2pdf(bis);
//
//        System.out.println("打印的PDF地址：" + new String(bytes));

    }

    /**
     * POI提供了HSSF、XSSF以及SXSSF三种方式操作Excel。
     * HSSF：Excel97-2003版本，扩展名为.xls。一个sheet最大行数65536，最大列数256。
     * XSSF：Excel2007版本开始，扩展名为.xlsx。一个sheet最大行数1048576，最大列数16384。
     * SXSSF：是在XSSF基础上，POI3.8版本开始提供的支持低内存占用的操作方式，扩展名为.xlsx。
     */
}
