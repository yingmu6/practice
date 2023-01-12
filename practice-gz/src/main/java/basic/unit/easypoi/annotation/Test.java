package basic.unit.easypoi.annotation;

import cn.afterturn.easypoi.entity.ImageEntity;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Easypoi注解方式 测试
 *
 * @Author chenSy
 * @Date 2023/01/12 11:22
 * @Description
 */
public class Test {
    public static void main(String[] args) throws Exception{

        List<StudentEntity> list = new ArrayList<>();
        StudentEntity student1 = new StudentEntity();
        student1.setId("101");
        student1.setName("张三ddd");
        student1.setSex(15);
        student1.setBirthday(new Date());
        student1.setRegistrationDate(new Date());

        // 按字符串形式设置图片，会从指定位置读取文件
        student1.setBarCode("D:\\self_project\\practice\\practice-gz\\src\\main\\java\\basic\\unit\\easypoi\\annotation\\a.png");

        StudentEntity student2 = new StudentEntity();
        student2.setId("102");
        student2.setName("李四");
        student2.setSex(16);
        student2.setBirthday(new Date());
        student2.setRegistrationDate(new Date());
        student2.setBarCode("D:\\self_project\\practice\\practice-gz\\src\\main\\java\\basic\\unit\\easypoi\\annotation\\b.png");

        list.add(student1);
        list.add(student2);

        // 按字节数组设置图片
        File temp1 = new File("D:\\self_project\\practice\\practice-gz\\src\\main\\java\\basic\\unit\\easypoi\\annotation\\student1.jpeg");
        InputStream is1 = new FileInputStream(temp1);
        byte[] b1 = new byte[8 * 1024 * 1024];
        is1.read(b1); //使用输入流读取图片内容
        student1.setPicture(b1);

        File temp2 = new File("D:\\self_project\\practice\\practice-gz\\src\\main\\java\\basic\\unit\\easypoi\\annotation\\student2.jpg");
        InputStream is2 = new FileInputStream(temp2);
        byte[] b2 = new byte[8 * 1024 * 1024];
        is2.read(b2);
        student2.setPicture(b2);

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机一班学生","学生的表格"),
                StudentEntity .class, list);

        // 操作Excel中指定单元格
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(2); //取值的话，是取已有表格的值，若没有改单元格，就会为null，所以要注意空指针
        Cell cell = row.getCell(0);
        cell.setCellValue("张三333dd"); //是引用传递的


        Row row1 = ((HSSFSheet)sheet).createRow(7); //Excel中新增一列（文本格式）
        Cell cell1 = row1.createCell(2);
        cell1.setCellValue("哈哈哈00");

        Row row2 = ((HSSFSheet)sheet).createRow(8); //Excel中新增一列（文本格式）
        Cell cell2 = row1.createCell(3);
        cell2.setCellValue("哈哈哈");

        ImageEntity imageEntity = new ImageEntity(); //设置图片
        imageEntity.setType(ImageEntity.URL);
        imageEntity.setUrl("D:\\self_project\\practice\\practice-gz\\src\\main\\java\\basic\\unit\\easypoi\\annotation\\student1.jpeg");
        imageEntity.setRowspan(8);
        imageEntity.setColspan(5);
        imageEntity.setWidth(10);
        imageEntity.setHeight(5);



        // 将数据写到数据流中
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);

        String fileName = "D:\\self_project\\practice\\practice-gz\\src\\main\\java\\basic\\unit\\easypoi\\tmp\\annotation.xls";

        // 将输出流数据写到文件中
        File file = new File(fileName);
        OutputStream outputStream = new FileOutputStream(file);
        outputStream.write(bos.toByteArray());
        outputStream.flush();
    }
}
