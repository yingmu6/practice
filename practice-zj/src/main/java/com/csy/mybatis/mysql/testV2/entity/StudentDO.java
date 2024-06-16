package com.csy.mybatis.mysql.testV2.entity;

import com.csy.mybatis.mysql.testV2.dao.BaseDO;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author chenSy
 * @Date 2023/02/20 20:07
 * @Description
 */
@Setter
@Getter
public class StudentDO extends BaseDO<Integer> {

    private String enterpriseNo;

    private String name;

    private Integer age;

    // 学号
    private String studentId;

    private Integer score;

    private String extVal;
}
