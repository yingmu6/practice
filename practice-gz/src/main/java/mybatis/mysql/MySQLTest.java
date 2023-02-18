package mybatis.mysql;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * Mybatis功能测试
 *
 * @Author chenSy
 * @Date 2023/02/16 17:28
 * @Description
 */
public class MySQLTest {

    /**
     * 功能介绍：https://mybatis.org/mybatis-3/getting-started.html
     * 1）Every MyBatis application centers around an instance of SqlSessionFactory. //每个Mybatis应用就对应一个SqlSessionFactory
     * 2）Building a SqlSessionFactory instance from an XML file is very simple. //可通过XML配置文件来构建SqlSessionFactory
     */
    public static final String MYSQL_CONFIG = "practice-gz/src/main/java/mybatis/mysql/MybatisConfig.xml";

    private static SqlSessionFactory sqlMapper;

    public static void main(String[] args) throws FileNotFoundException {
        init();
        testGetUsers();
    }

    public static void testGetUsers() { //直接对数据操作
        SqlSession session = sqlMapper.openSession();
        try {
            IUserMapper userMapper = session.getMapper(IUserMapper.class);

            List<User> users = userMapper.getUserList();
            System.out.println("获取的数量：" + users.size());

            User user = userMapper.getUser(11);
            System.out.println("详情信息：" + JSON.toJSONString(user));

        } finally {
            session.close();
        }
    }

    public static void init() throws FileNotFoundException{
        final Reader reader = getReader(MYSQL_CONFIG);
        sqlMapper = new SqlSessionFactoryBuilder().build(reader);
    }

    public static Reader getReader(String resource) throws FileNotFoundException {
        File file = new File(resource);
        FileInputStream ins = new FileInputStream(file);
        Reader reader = new InputStreamReader(ins);
        return reader;
    }
}
