package com.csy.design.proxy.static_p.test;


import com.csy.design.proxy.static_p.IUserDao;

/**
 * @author chensy
 * @date 2021/10/27
 */
public class UserProxyDao implements IUserDao {
    /**
     * 1）代理对象与真实对象都实现相同的接口
     * 2）代理对象持有真实对象的引用
     * 3）调用代理对象的方法时，会在代理类的方法中调用真实对象的方法，
     * 并且可以加上调用前、调用后的逻辑处理
     */
    private IUserDao userDao;

    public UserProxyDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save(String str) {
        System.out.println("目标方法调用之前");
        this.userDao.save(str);
        System.out.println("目标方法调用之后");
    }
}
