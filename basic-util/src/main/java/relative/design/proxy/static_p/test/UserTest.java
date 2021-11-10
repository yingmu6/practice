package relative.design.proxy.static_p.test;

import relative.design.proxy.IUserDao;
import relative.design.proxy.static_p.UserDao;

/**
 * 静态代理：
 * 优点：可以在不修改目标对象的前提下扩展目标对象的功能。
 * 缺点：
 * 冗余：由于代理对象要实现与目标对象一致的接口，会产生过多的代理类。
 * 不易维护：一旦接口增加方法，目标对象与代理对象都要进行修改。
 *
 * @author chensy
 * @date 2021/10/27
 */
public class UserTest {
    public static void main(String[] args) {
        IUserDao userDao = new UserDao();
        userDao.save("haha");

        IUserDao realObj = new UserDao();
        UserProxyDao userProxyDao = new UserProxyDao(realObj);
        userProxyDao.save("xxx");
    }
}
