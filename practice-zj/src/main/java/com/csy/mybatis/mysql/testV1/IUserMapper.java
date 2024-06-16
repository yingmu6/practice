package com.csy.mybatis.mysql.testV1;

import java.util.List;

/**
 * @Author chenSy
 * @Date 2023/02/16 18:25
 * @Description
 */
public interface IUserMapper {
    List<User> getUserList();

    User getUser(int id);
}
