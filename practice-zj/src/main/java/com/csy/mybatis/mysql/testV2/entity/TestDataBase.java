package com.csy.mybatis.mysql.testV2.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author chenSy
 * @Date 2023/04/13 20:32
 * @Description
 */
@Setter
@Getter
public class TestDataBase {

    // 类别名称；它必须与存储在数据库中的类别名称匹配；该参数为 "" 表示获取没有类别的那些描述；为 null 则表示该类别名称不应该用于缩小搜索范围
    private String catalog;

    // schemaPattern - 模式名称的模式；它必须与存储在数据库中的模式名称匹配；该参数为 "" 表示获取没有模式的那些描述；为 null
    // 则表示该模式名称不应该用于缩小搜索范围
    private String schema;

    // 表格的集合
    private final Map<String, TestTable> tables = new HashMap<String, TestTable>();

    public TestDataBase(String catalog, String schema) {
        this.catalog = catalog;
        this.schema = schema;
    }

    public TestTable getTable(String tableName) {
        return tables.get(tableName);
    }

    public void addTable(TestTable testTable) {
        tables.put(testTable.getName(), testTable);
    }

    public String[] getTableNames() {
        return tables.keySet().toArray(new String[tables.keySet().size()]);
    }
}
