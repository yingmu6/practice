package com.csy.mybatis.mysql.testV2.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author chenSy
 * @Date 2023/04/13 20:29
 * @Description
 */
@Setter
@Getter
public class TestColumn {
    // 列名
    private String name;
    // 列的类型
    private int type;

    public TestColumn(String name, int type) {
        this.name = name;
        this.type = type;
    }
}
