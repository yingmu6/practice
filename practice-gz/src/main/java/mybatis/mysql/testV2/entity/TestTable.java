package mybatis.mysql.testV2.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author chenSy
 * @Date 2023/04/13 20:30
 * @Description
 */
@Setter
@Getter
public class TestTable {

    //表名
    private String name;
    //类别名
    private String catalog;
    //模式名
    private String schema;
    //列的集合
    private final Map<String, TestColumn> columns = new HashMap<String, TestColumn>();
    //主键列
    private TestColumn primaryKey;

    public TestTable(String name) {
        this.name = name;
    }

    public void addColumn(TestColumn column) {
        columns.put(column.getName(), column);
    }

    public TestColumn getColumn(String columnName) {
        return columns.get(columnName);
    }

    public String[] getColumns() {
        return columns.keySet().toArray(new String[0]);
    }
}
