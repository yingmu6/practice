package mybatis.derby;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.executor.result.DefaultResultHandler;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

/**
 * @Author chenSy
 * @Date 2023/02/16 21:40
 * @Description
 */
public class DerbyTest {

    /**
     * 苍天啊，居然是src目标下不能解析resource、xml
     * 1）在maven中配置build
     * 2）将xml、properties移到resources目录下
     *
     * 查看解决文档
     * https://blog.csdn.net/jeanFlower/article/details/74893387
     */
    public static final String BLOG_PROPERTIES = "META-INF/mybatis/db-derby/blog-derby.properties";

    public static final String BLOG_DDL = "META-INF/mybatis/db-derby/sql/blog-derby-schema.sql";

    public static final String BLOG_DATA = "META-INF/mybatis/db-derby/sql/blog-derby-dataload.sql";

    public static final String CONFIG = "META-INF/mybatis/db-derby/config/MapperConfig.xml";

    private static SqlSessionFactory sqlMapper;

    @Before
    public void init() throws Exception {
        createBlogDataSource();
        final Reader reader = Resources.getResourceAsReader(CONFIG);
        sqlMapper = new SqlSessionFactoryBuilder().build(reader);
    }

    @Test
    public void testGetList() {
        SqlSession session = sqlMapper.openSession();
        try {
            DefaultResultHandler handler = new DefaultResultHandler();
            AuthorMapper mapper = session.getMapper(AuthorMapper.class);
            mapper.selectAllAuthors(handler);
            assertEquals(2, handler.getResultList().size());

            // 此处要把上面mapper.selectAllAuthors(handler); 注释掉才有值
            List<Author> selectAllAuthors = mapper.selectAllAuthors();
            System.out.println("获取的数量：" + selectAllAuthors.size());

            //获取详情（此处有值）
            Author author = mapper.selectAuthor(101);
            System.out.println("详情信息：" + JSON.toJSONString(author));

        } finally {
            session.close();
        }
    }

    public UnpooledDataSource createUnpooledDataSource(String resource) throws IOException {
        Properties props = Resources.getResourceAsProperties(resource);
        UnpooledDataSource ds = new UnpooledDataSource();
        ds.setDriver(props.getProperty("driver"));
        ds.setUrl(props.getProperty("url"));
        ds.setUsername(props.getProperty("username"));
        ds.setPassword(props.getProperty("password"));
        return ds;
    }

    public void runScript(DataSource ds, String resource) throws IOException, SQLException {
        Connection connection = ds.getConnection();
        try {
            ScriptRunner runner = new ScriptRunner(connection);
            runner.setAutoCommit(true);
            runner.setStopOnError(false);
            runner.setLogWriter(null);
            runner.setErrorLogWriter(null);
            runScript(runner, resource);
        } finally {
            connection.close();
        }
    }

    public void runScript(ScriptRunner runner, String resource) throws IOException, SQLException {
        Reader reader = Resources.getResourceAsReader(resource);
        try {
            runner.runScript(reader);
        } finally {
            reader.close();
        }
    }

    public DataSource createBlogDataSource() throws IOException, SQLException {
        DataSource ds = createUnpooledDataSource(BLOG_PROPERTIES);
        runScript(ds, BLOG_DDL);
        runScript(ds, BLOG_DATA);
        return ds;
    }
}
