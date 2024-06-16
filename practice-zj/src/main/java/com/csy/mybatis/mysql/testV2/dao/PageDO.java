package com.csy.mybatis.mysql.testV2.dao;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author chenSy
 * @Date 2023/03/17 18:45
 * @Description
 */
@Getter
@Setter
public class PageDO<T> {

    private int pageIndex = 1;

    private int pageSize = 20;

    private String orderBy;

    private long total = 0L;

    private int pageCount;

    private List<T> rows = new ArrayList();
}
