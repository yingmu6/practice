package com.csy.dao.test;

import mybatis.mysql.testV2.dao.IStudentDAO;
import mybatis.mysql.testV2.entity.StudentDO;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * @Author chenSy
 * @Date 2023/04/01 15:52
 * @Description
 */
public class MybatisByJunitTest extends AbstractDaoTest {


    private static final String DEFAULT_ERROR_DESCRIBE = "获取的信息异常";

    @Resource
    private IStudentDAO studentDAO;

    /**
     * mybatis使用场景1：使用mybatis能增、删、改、查、分页查询
     * 注意点：
     * 1）需要设置@Rollback(value = false)，才能把新增、修改、删除的数据提交到数据库，否则junit测试完后就会回滚
     * (此处是junit比main测试的优势之一：junit测试后可以回滚处理的数据，能够完成功能测试，并且不影响数据库数据；而main方法直接就提交到数据库了)
     *
     * 2）mybatis的配置文件中，如spring-database-test.xml需要配置事务管理器，如 transactionManager，否则会报出“No bean named 'transactionManager' available”
     *
     * 3）调用xml时，要小心参数值，因为参数是Object类型，哪种类型都可以传递，所以在编译期间是看不出来的，
     * 例如：BaseDAO#get，如调用是为 return this.queryForObject(this.nameSpace + "get", enterpriseNo);（XML中的id、enterpriseNo都填写为enterpriseNo的值，就比较难以排查了）
     *
     * 4）使用@Rollback(value = false)注解提交事务后，会打印如下日志（若没有设置，或@Rollback(value = true)，就不会打印下面的日志，搜索关键字“Committed” ）
     * [ INFO] [practice-test-stdout] 2023-04-03 17:21:59: 2587 [main] ( TransactionContext.java,140 ) - Committed transaction for test: [DefaultTestContext@551aa95a testClass = MybatisByJunitTest....
     */
    @Test
    public void test_getByStudentId() {
        String studentId = "1001";
        StudentDO studentDO = studentDAO.getByStudentId(studentId);
        Assert.notNull(studentDO, DEFAULT_ERROR_DESCRIBE);
        Assert.isTrue(String.valueOf(studentDO.getStudentId()).equals(studentId), DEFAULT_ERROR_DESCRIBE);
    }

    @Rollback(value = false)
    @Test
    public void test_addStudent() {
        StudentDO existDO = studentDAO.getByStudentId("5001");
        if (existDO != null) {
            return;
        }
        StudentDO addDO = new StudentDO();
        addDO.setEnterpriseNo("ff3c");
        addDO.setName("李李");
        addDO.setAge(14);
        addDO.setScore(79);
        addDO.setStudentId("5001");
        studentDAO.save(addDO);

        StudentDO dbDO = studentDAO.getByStudentId("5001");
        Assert.notNull(dbDO, DEFAULT_ERROR_DESCRIBE);
        Assert.isTrue(dbDO.getName().equals("李李"), DEFAULT_ERROR_DESCRIBE);
    }

    @Rollback(value = false)
    @Test
    public void test_removeById() {
        studentDAO.remove(23, "ff3c");

        StudentDO studentDO = studentDAO.get(23, "ff3c");
        Assert.isNull(studentDO, DEFAULT_ERROR_DESCRIBE);
    }

//    @Rollback(value = false)
    @Test
    public void test_update() {
        StudentDO studentDO = new StudentDO();
        studentDO.setId(8);
        studentDO.setName("刘六444");

        studentDAO.update(studentDO);
        StudentDO existDO = studentDAO.get(8, "33a");
        Assert.notNull(existDO, DEFAULT_ERROR_DESCRIBE);

        StudentDO existDO2 = studentDAO.getByStudentId("4001");
        Assert.notNull(existDO2, DEFAULT_ERROR_DESCRIBE);
        Assert.isTrue(existDO2.getName().equals("刘六444"), DEFAULT_ERROR_DESCRIBE);
    }

    @Test
    public void test_getByPage() {

    }

     /**
     * mybatis使用场景2：能够进行事务处理
     */

    /**
     * mybatis映射XML的标签元素使用
     * 如<select/>、<update/>等等
     */
}
