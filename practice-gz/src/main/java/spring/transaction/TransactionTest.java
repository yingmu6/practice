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
     * 参考链接：
     * 1）https://docs.spring.io/spring-framework/docs/3.0.0.M3/reference/html/ch11s06.html spring关于编程式事务的介绍
     * 2）https://docs.spring.io/spring-framework/docs/3.0.0.M3/reference/html/ch11s05.html spring的事务介绍
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
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void basicUseByXml_V3_With_Transaction() { //测试带有事务的场景（未生效）
        //studentDO插入记录正常，userAttrDO都插入记录异常（带有事务）
        insertStudentByNormal(studentDAO);
        insertUserAttrByError(userAttrDAO);
    }

    private void insertStudentByNormal(IStudentDAO studentDAO) {
        StudentDO addDO = new StudentDO();
        addDO.setEnterpriseNo("33a");
        addDO.setName("刘六888");
        addDO.setAge(18);
        addDO.setScore(88);
        addDO.setStudentId("8001");
        studentDAO.save(addDO);
    }

    private void insertStudentByError(IStudentDAO studentDAO) {
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

    private void insertUserAttrByError(IUserAttrDAO userAttrDAO) {
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
