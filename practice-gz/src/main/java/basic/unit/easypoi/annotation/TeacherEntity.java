package basic.unit.easypoi.annotation;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.io.Serializable;

/**
 * @Author chenSy
 * @Date 2023/01/12 11:18
 * @Description
 */
@ExcelTarget("teacherEntity")
public class TeacherEntity implements Serializable {

    /** name */
    @Excel(name = "主讲老师_teacherEntity,代课老师_absent", orderNum = "1", mergeVertical = true,needMerge=true,isImportField = "true_major,true_absent")
    private String name;
}
