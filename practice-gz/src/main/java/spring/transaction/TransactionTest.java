package spring.transaction;

import mybatis.mysql.testV2.dao.IStudentDAO;
import mybatis.mysql.testV2.dao.IUserAttrDAO;
import mybatis.mysql.testV2.entity.StudentDO;
import mybatis.mysql.testV2.entity.UserAttrDO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 事务测试
 * @Author chenSy
 * @Date 2023/04/26 14:27
 * @Description
 */
public class TransactionTest {

    /**
     * 编程式事务_概述：
     * 1）The Spring Framework provides two means of programmatic transaction management:
     * a）Using the TransactionTemplate.
     * b）Using a PlatformTransactionManager implementation directly.
     * If you are going to use programmatic transaction management, the Spring team generally recommends using the TransactionTemplate.
     * The second approach is similar to using the JTA UserTransaction API (although exception handling is less cumbersome).
     * （两种编程方式，spring团队推荐使用TransactionTemplate）
     *
     * 声明式事务_概述：
     * 1）The Spring Framework's declarative transaction management is made possible with Spring AOP
     * （声明式时间，会带着AOP方式处理）
     *
     * @Transactional注解使用_概述
     * 1）
     *
     * 事务相关概念_概述：
     * 1）事务指逻辑上的一组操作，组成这组操作的各个单元，要不全部成功，要不全部不成功。
     *    a）原子性(Atomicity): 事务开始后所有操作，要么全部做完，要么全部不做，不可能停滞在中间环节
     *    b）一致性(Consistency): 事务开始前和结束后，数据库的完整性约束没有被破坏。
     *    c）隔离性(Isolation): 同一时间，只允许一个事务请求同一数据，不同的事务之间彼此没有任何干扰。
     *    d）持久性(Durability): 事务完成后，事务对数据库的所有更新将被保存到数据库，不能回滚。
     * 2）传播行为：当事务方法被另一个事务方法调用时，必须指定事务应该如何传播。例如：方法可能继续在现有事务中运行，也可能开启一个新事务，
     *           并在自己的事务中运行。Spring定义了七种传播行为：如：TransactionDefinition.PROPAGATION_REQUIRED、TransactionDefinition.PROPAGATION_SUPPORTS等等
     * 3）隔离级别定义了一个事务可能受其他并发事务影响的程度。
     *   多个事务并发运行，经常会操作相同的数据来完成各自的任务。在这种情况下可能会导致以下的问题:
     *   a）脏读（Dirty reads）—— 事务A读取了事务B更新的数据，然后B回滚操作，那么A读取到的数据是脏数据。
     *   b）不可重复读（Nonrepeatable read）—— 事务 A 多次读取同一数据，事务 B 在事务A多次读取的过程中，对数据作了更新并提交，导致事务A多次读取同一数据时，结果不一致。
     *   c）幻读（Phantom read）—— 系统管理员A将数据库中所有学生的成绩从具体分数改为ABCDE等级，但是系统管理员B就在这个时候插入了一条具体分数的记录，当系统管理员A改结束后发现还有一条记录没有改过来，就好像发生了幻觉一样，这就叫幻读。
     * 4）回滚规则：事务回滚规则定义了哪些异常会导致事务回滚而哪些不会。默认情况下，只有未检查异常(RuntimeException和Error类型的异常)会导致事务回滚。
     * 5）事务超时：为了使应用程序很好地运行，事务不能运行太长的时间。因为事务可能涉及对后端数据库的锁定，也会占用数据库资源。事务超时就是事务的一个定时器，在特定时间内事务如果没有执行完毕，那么就会自动回滚，而不是一直等待其结束
     * 6）是否只读：如果在一个事务中所有关于数据库的操作都是只读的，也就是说，这些操作只读取数据库中的数据，而并不更新数据,　这个时候我们应该给该事务设置只读属性，这样可以帮助数据库引擎优化事务。提升效率。
     *
     * 参考链接：
     * 1）https://docs.spring.io/spring-framework/docs/3.0.0.M3/reference/html/ch11s06.html spring关于编程式事务的介绍
     * 2）https://docs.spring.io/spring-framework/docs/3.0.0.M3/reference/html/ch11s05.html spring的事务介绍
     * 3）https://www.marcobehler.com/guides/spring-transaction-management-transactional-in-depth @Transactional介绍
     * 4）https://www.jianshu.com/p/befc2d73e487 事务简介（中文版，还可以的）
     */

    private IStudentDAO studentDAO;

    private IUserAttrDAO userAttrDAO;

    @Before
    public void init() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/spring/spring-db-config-transaction.xml");
        studentDAO = (IStudentDAO) applicationContext.getBean("studentDAO");
        userAttrDAO = (IUserAttrDAO) applicationContext.getBean("userAttrDAO");
    }

    /**
     * 场景1：基本使用使用事务
     * 1）使用xml方式扫描事务
     * 2）使用注解方式处理事务
     */
    @Test
    @Rollback(value = false)
    public void basicUseByXml_V1_Without_Transaction() {
        //studentDO与userAttrDO都插入记录正常（没有带事务）
        insertStudentByNormal(studentDAO);
        insertUserAttrByNormal(userAttrDAO);
    }

    @Test
    @Rollback(value = false)
    public void basicUseByXml_V2_Without_Transaction() {
        //studentDO插入记录正常，userAttrDO都插入记录异常（没有带事务）
        insertStudentByNormal(studentDAO); //没有事务保护时，第二张表可以添加异常，第一张表还正常提交（没有达到整体事务保护）
        insertUserAttrByError(userAttrDAO);
    }

    @Test
    @Rollback(value = false)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void basicUseByXml_V3_With_Transaction() { //测试带有事务的场景（未生效）
        //studentDO插入记录正常，userAttrDO都插入记录异常（带有事务）
        insertStudentByNormal(studentDAO);
        insertUserAttrByError(userAttrDAO);
    }

    public void insertStudentByNormal(IStudentDAO studentDAO) {
        StudentDO addDO = new StudentDO();
        addDO.setEnterpriseNo("33a");
        addDO.setName("刘六888");
        addDO.setAge(18);
        addDO.setScore(88);
        addDO.setStudentId("8001");
        studentDAO.save(addDO);
    }

    public void insertStudentByError(IStudentDAO studentDAO) {
        throw new RuntimeException("insert student happen error!");
    }

    private void insertUserAttrByNormal(IUserAttrDAO userAttrDAO) {
        UserAttrDO userAttrDO = new UserAttrDO();
        userAttrDO.setName("王五");
        userAttrDO.setFirstAttr1("xxx111");
        userAttrDO.setFirstAttr2("xxx222");
        userAttrDO.setSecondAttr1("yyy111");
        userAttrDO.setSecondAttr2("yyy222");
        userAttrDAO.save(userAttrDO);
    }

    public void insertUserAttrByError(IUserAttrDAO userAttrDAO) {
        throw new RuntimeException("insert userAttr happen error!");
    }

    /**
     * 场景2：基本使用编程式事务（能解决事务穿透问题）
     */

    /**
     * 场景3：编程式事务的另一种方式PlatformTransactionManager使用
     * （总共两种方式：PlatformTransactionManager和TransactionTemplate）
     */

    /**
     * 场景4：事务的传播属性propagation、隔离级别isolation
     */
}
