package com.csy.design.proxy.static_p;

/**
 * @author chensy
 * @date 2021/10/27
 */
public class UserDao implements IUserDao {
    @Override
    public void save(String str) {
        System.out.println("真实对象，保存用户数据：" + str);
    }
}
