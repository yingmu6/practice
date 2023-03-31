package mybatis.mysql.testV1;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.util.List;

/**
 * Mybatis功能测试
 *
 * @Author chenSy
 * @Date 2023/02/16 17:28
 * @Description
 */
public class UserDAOTest {

    /**
     * 功能介绍：https://mybatis.org/mybatis-3/getting-started.html
     * 1）Every MyBatis application centers around an instance of SqlSessionFactory. //每个Mybatis应用就对应一个SqlSessionFactory
     * 2）Building a SqlSessionFactory instance from an XML file is very simple. //可通过XML配置文件来构建SqlSessionFactory
     */
    public static final String MYSQL_CONFIG = "META-INF/mybatis/db-mysql/test-v1/conf/mybatis-config-v1.xml";

    private static SqlSessionFactory sqlMapper;

    @Test
    public void testGetUsers() { //直接对数据操作
        SqlSession session = sqlMapper.openSession();
        try {
            IUserMapper userMapper = session.getMapper(IUserMapper.class);

            List<User> users = userMapper.getUserList();
            System.out.println("获取的数量：" + users.size() + ",时间：" + System.currentTimeMillis());

            User user = userMapper.getUser(11);
            System.out.println("详情信息：" + JSON.toJSONString(user));

        } finally {
            session.close();
        }
    }

    @Before
    public void init() throws Exception {
        final Reader reader = Resources.getResourceAsReader(MYSQL_CONFIG);
        sqlMapper = new SqlSessionFactoryBuilder().build(reader);
    }
}
