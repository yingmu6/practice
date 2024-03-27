package mybatis.mysql.testV2;

import com.alibaba.fastjson.JSON;
import mybatis.mysql.testV2.dao.IStudentDAO;
import mybatis.mysql.testV2.entity.StudentDO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author chenSy
 * @Date 2023/02/20 20:18
 * @Description
 */
public class MybatisTest {

    /**
     * Mybatis的使用
     * 1）SqlSession是Mybatis中用于与数据库交互的对象。封装了一系列操作数据库方法，如CRUD。所以，每次进行数据库操作都是通过SqlSession的。
     *    SqlSession是线程不安全的，每次使用之前需要创建一个新的对象，使用完成后并将它关闭。
     *
     * 2）在当前用例中，DAO对应的实例，都会继承BaseDAO，而BaseDAO又会继承SqlSessionDaoSupport（该类是为了方便方位SqlSession）
     *    而在BaseDAO中重写了setSqlSessionFactory(...)方法，并使用@Resource(name = "sqlSessionFactory")修饰方法，这个就会在DAO实例初始化中，进行依赖注入
     *    SqlSession就是在此时被实例化的。
     *
     * 3）Druid是阿里巴巴开源的一款JDBC组件，是一款数据库连接池，对MySQL的适配性和功能很强大，
     *   包括监控数据库访问性能、数据库密码加密、SQL执行日志、以及拓展监控的实现等等 。
     *
     * 参考链接：
     * 1）https://www.cnblogs.com/BlogNetSpace/p/17198458.html  SqlSession的使用
     * 2）https://cloud.tencent.com/developer/article/1835979  Druid连接池使用
     */

    private IStudentDAO studentDAO;

    /**
     * 初始化：在测试调用前，加载数据库配置，获取对应的DAO对象
     */
    @Before
    public void init() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/spring/spring-database-config.xml");
        studentDAO = (IStudentDAO) applicationContext.getBean("studentDAO");
    }

    /**
     * 场景1：查询数据
     */
    @Test
    public void getByStudentId() {
        StudentDO studentDO1 = studentDAO.getByStudentId("3001");
        System.out.println("查询到的记录：" + JSON.toJSONString(studentDO1));

        /**
         * 输出结果：
         * 1）查询到记录的输出
         * 查询到的记录：{"age":15,"extVal":"test","gmtCreate":1711366784,"gmtModified":1711366784,"id":1,"name":"刘六2","score":85,"studentId":"3001"}
         *
         * 2）未查询到记录的输出：
         * 查询到的记录：null
         *
         * 结果分析：
         * 1）DAO的实例都继承了BaseDAO，并且使用@Resouce(...)进行修饰，表明是Spring的bean，然后再通过BaseDAO封装的方法，访问数据库
         *
         * 总结概括：
         *
         */
    }

    /**
     * 场景2：添加数据
     */
    @Test
    public void addStudent() {
        String studentId = "3001";
        // 先判断数据中是否存在，再添加，避免重复数据
        StudentDO oldDO = studentDAO.getByStudentId(studentId);
        if (oldDO != null) {
            return;
        }

        StudentDO addDO = new StudentDO();
        addDO.setEnterpriseNo("33a");
        addDO.setName("刘六2");
        addDO.setAge(15);
        addDO.setScore(85);
        addDO.setStudentId(studentId);
        addDO.setExtVal("test");

        long curTime = System.currentTimeMillis() / 1000;
        addDO.setGmtCreate(curTime);
        addDO.setGmtModified(curTime);

        studentDAO.save(addDO);
    }

    /**
     * 场景3：删除数据
     */
    @Test
    public void deleteStudent() {
        studentDAO.remove(6, "11a");
    }

    /**
     * 场景4：更新数据
     */
    @Test
    public void updateStudent() {
        StudentDO studentDO = new StudentDO();
        studentDO.setId(3);
        studentDO.setName("wangwu888");

        studentDAO.update(studentDO);
    }
}
