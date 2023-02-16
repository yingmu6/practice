package mybatis.mysql;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

/**
 * Mybatis功能测试
 *
 * @Author chenSy
 * @Date 2023/02/16 17:28
 * @Description
 */
public class MybatisTest {

    /**
     * 功能介绍：https://mybatis.org/mybatis-3/getting-started.html
     * 1）Every MyBatis application centers around an instance of SqlSessionFactory. //每个Mybatis应用就对应一个SqlSessionFactory
     * 2）Building a SqlSessionFactory instance from an XML file is very simple. //可通过XML配置文件来构建SqlSessionFactory
     *
     */
    public static void main(String[] args) {
//        DataSource dataSource = BlogDataSourceFactory.getBlogDataSource();
//        TransactionFactory transactionFactory =
//                new JdbcTransactionFactory();
//        Environment environment =
//                new Environment("development", transactionFactory, dataSource);
//        Configuration configuration = new Configuration(environment);
//        configuration.addMapper(BlogMapper.class);
//        SqlSessionFactory sqlSessionFactory =
//                new SqlSessionFactoryBuilder().build(configuration);
    }
}
