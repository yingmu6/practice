package com.csy.mybatis.mysql.testV2.entity;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author chenSy
 * @Date 2023/04/13 20:33
 * @Description
 */
public class TestDataBaseFactory {

    // 根据数据库连接-类别-模式，生成Database对象。
    public static TestDataBase newDatabase(Connection conn, String catalogFilter,
                                           String schemaFilter) throws SQLException {
        TestDataBase database = new TestDataBase(catalogFilter, schemaFilter);
        ResultSet rs = null;
        try {
            // 数据库元数据
            DatabaseMetaData dbmd = conn.getMetaData();

            try {
                rs = dbmd.getColumns(catalogFilter, schemaFilter, null, null);
                // 遍历结果集，取出表的信息，构建Table对象，加入到Databse的Table集合中
                while (rs.next()) {
                    String catalogName = rs.getString("TABLE_CAT");
                    String schemaName = rs.getString("TABLE_SCHEM");
                    String tableName = rs.getString("TABLE_NAME");
                    String columnName = rs.getString("COLUMN_NAME");
                    int dataType = Integer.parseInt(rs.getString("DATA_TYPE"));
                    TestTable table = database.getTable(tableName);
                    if (table == null) {
                        table = new TestTable(tableName);
                        table.setCatalog(catalogName);
                        table.setSchema(schemaName);
                        database.addTable(table);
                    }
                    table.addColumn(new TestColumn(columnName, dataType));
                }
            } finally {
                if (rs != null)
                    rs.close();
            }

            try {
                String[] tableNames = database.getTableNames();
                // 遍历所有的Table，取出列名和主键信息
                for (int i = 0; i < tableNames.length; i++) {
                    TestTable table = database.getTable(tableNames[i]);
                    rs = dbmd.getPrimaryKeys(catalogFilter, schemaFilter,
                            table.getName());
                    if (rs.next()) {
                        String columnName = rs.getString("COLUMN_NAME");
                        table.setPrimaryKey(table.getColumn(columnName));
                    }
                }
            } finally {
                if (rs != null)
                    rs.close();
            }

        } finally {
            try {
                conn.rollback();
            } catch (Exception e) {
                /* ignore */
            }
        }
        return database;
    }


}
