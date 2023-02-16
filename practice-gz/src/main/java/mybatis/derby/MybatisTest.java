package mybatis.derby;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.executor.result.DefaultResultHandler;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
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
public class MybatisTest {

    /**
     * 苍天啊，居然是src目标下不能解析resource、xml
     * 1）在maven中配置build
     * 2）将xml、properties移到resources目录下
     *
     * 查看解决文档
     * https://blog.csdn.net/jeanFlower/article/details/74893387
     */
    public static final String BLOG_PROPERTIES = "practice-gz/src/main/java/mybatis/derby/blog-derby.properties";

    public static final String BLOG_DDL = "practice-gz/src/main/java/mybatis/derby/blog-derby-schema.sql";

    public static final String BLOG_DATA = "practice-gz/src/main/java/mybatis/derby/blog-derby-dataload.sql";

    public static final String CONFIG = "practice-gz/src/main/java/mybatis/derby/MapperConfig.xml";

    private static SqlSessionFactory sqlMapper;

    public static void main(String[] args) throws Exception {
//       testFile(); //直接加载是可以，说明类路径是可以的，那就得看Mybatis底层是怎么加载的 org.apache.ibatis.io.Resources#getResourceAsProperties
        init();
        testGetList();
//        System.in.read();
    }

    public static void init() throws Exception {
        createBlogDataSource();
//        final Reader reader = Resources.getResourceAsReader(CONFIG);
        final Reader reader = getReader(CONFIG);
        sqlMapper = new SqlSessionFactoryBuilder().build(reader);
    }

    public static void testGetList() {
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

    public static UnpooledDataSource createUnpooledDataSource(String resource) throws IOException {
//        Properties props = Resources.getResourceAsProperties(resource); //换回原来写法
        Properties props = getProperties(resource);
        UnpooledDataSource ds = new UnpooledDataSource();
        ds.setDriver(props.getProperty("driver"));
        ds.setUrl(props.getProperty("url"));
        ds.setUsername(props.getProperty("username"));
        ds.setPassword(props.getProperty("password"));
        return ds;
    }

    public static PooledDataSource createPooledDataSource(String resource) throws IOException {
        Properties props = Resources.getResourceAsProperties(resource);
        PooledDataSource ds = new PooledDataSource();
        ds.setDriver(props.getProperty("driver"));
        ds.setUrl(props.getProperty("url"));
        ds.setUsername(props.getProperty("username"));
        ds.setPassword(props.getProperty("password"));
        return ds;
    }

    public static void runScript(DataSource ds, String resource) throws IOException, SQLException {
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

    public static void runScript(ScriptRunner runner, String resource) throws IOException, SQLException {
//        Reader reader = Resources.getResourceAsReader(resource);

        Reader reader = getReader(resource);
        try {
            runner.runScript(reader);
        } finally {
            reader.close();
        }
    }

    public static DataSource createBlogDataSource() throws IOException, SQLException {
        DataSource ds = createUnpooledDataSource(BLOG_PROPERTIES);
        runScript(ds, BLOG_DDL);
        runScript(ds, BLOG_DATA);
        return ds;
    }

    public static Reader getReader(String resource) throws FileNotFoundException {
        File file = new File(resource);
        FileInputStream ins = new FileInputStream(file);
        Reader reader = new InputStreamReader(ins);
        return reader;
    }
    public static Properties getProperties(String resource) throws IOException{
        File file = new File(resource);
        FileInputStream ins = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(ins);
        return properties;
    }

    public static void testFile() throws Exception {
        File file = new File("practice-gz/src/main/java/mybatis/derby/blog-derby.properties");
        FileInputStream ins = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(ins);
        System.out.println("打印属性值：" + properties);

        File file1 = new File("practice-gz/src/main/java/mybatis/derby/blog-derby-dataload.sql");
        System.out.println("是否存在：" + file1.exists());
    }
}
