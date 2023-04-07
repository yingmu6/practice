package com.csy.dao.test;

import com.google.common.collect.Lists;
import mybatis.mysql.testV2.condition.ListQueryCondition;
import mybatis.mysql.testV2.condition.PageQueryStudentCondition;
import mybatis.mysql.testV2.dao.IStudentDAO;
import mybatis.mysql.testV2.dao.IUserAttrDAO;
import mybatis.mysql.testV2.entity.StudentDO;
import mybatis.mysql.testV2.entity.UserAttrDO;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author chenSy
 * @Date 2023/04/01 15:52
 * @Description
 */
public class MybatisByJunitTest extends AbstractDaoTest {


    private static final String DEFAULT_ERROR_DESCRIBE = "获取的信息异常";

    @Resource
    private IStudentDAO studentDAO;

    @Resource
    private IUserAttrDAO userAttrDAO;

    /**
     * mybatis使用场景1：使用mybatis能增、删、改、查、分页查询、批量添加、批量修改
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
     *
     * 5）<trim>标签的使用方法（可用于产生动态SQL）
     * prefix：表示在trim包裹的SQL前添加指定内容
     * suffix：表示在trim包裹的SQL末尾添加指定内容
     * prefixOverrides：表示去掉（覆盖）trim包裹的SQL的指定首部内容
     * suffixOverrides：表示去掉（覆盖）trim包裹的SQL的指定尾部内容
     *
     * 6）mybatis动态sql中的两个内置参数（_parameter和_databaseId）
     *  _parameter:代表整个参数
     *             单个参数：_parameter就是这个参数
     *             多个参数：参数会被封装为一个map:_parameter就是代表这个map
     * _databaseId:如果配置了databaseIdProvider标签
     *             _databaseId 就是代表当前数据库的别名Oracle
     *
     * 7）各个标签的使用以及具体的属性说明，如<insert/><update/>等等
     *    参考：https://mybatis.org/mybatis-3/sqlmap-xml.html#Result_Maps
     *
     * 8）
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
        PageQueryStudentCondition condition = new PageQueryStudentCondition();
        condition.setEnterpriseNo("11a");
        int size = studentDAO.queryCountByCondition(condition);
        if (size == 0) {
            return;
        }

        List<StudentDO> totalStudentDOS = Lists.newArrayList();
        int pageSize = 2; //每页的条数
        int totalPageNum = this.calculatePageNum(size, pageSize);
        for (int pageNum = 1; pageNum <= totalPageNum; pageNum++) {
            // 设置分页参数
            condition.setOffset(this.calculateStart(pageNum, pageSize));
            condition.setLimit(pageSize);
            List<StudentDO> studentDOS = studentDAO.queryByCondition(condition);
            totalStudentDOS.addAll(studentDOS);
        }

        Assert.isTrue(totalStudentDOS.size() == size, DEFAULT_ERROR_DESCRIBE);
    }

    /**
     * 批量插入方式：
     * 1）insert into student(xxx,yyy...) values(11,22,...), (33,44,...)这种方式是可以的
     */

    @Rollback(value = false)
    @Test
    public void test_batchAdd() {
        List<StudentDO> studentDOS = Lists.newArrayList();
        StudentDO s1 = new StudentDO();
        s1.setEnterpriseNo("11a");
        s1.setName("王2");
        s1.setAge(13);
        s1.setScore(89);
        s1.setStudentId("6001");

        StudentDO s2 = new StudentDO();
        s2.setEnterpriseNo("11a");
        s2.setName("李2");
        s2.setAge(15);
        s2.setScore(69);
        s2.setStudentId("7001");

        studentDOS.add(s1);
        studentDOS.add(s2);

        ListQueryCondition condition = new ListQueryCondition();
        condition.setEnterpriseNo("11a");
//        List<StudentDO> oldDOS = studentDAO.findList(condition);

        studentDAO.saveBatch(studentDOS);

//        List<StudentDO> newDOS = studentDAO.findList(condition);
//        Assert.isTrue(oldDOS.size() + 2 == newDOS.size(), DEFAULT_ERROR_DESCRIBE);
    }

    @Rollback(value = false)
    @Test
    public void test_batchUpdate() {

        StudentDO s1 = new StudentDO();
        s1.setId(1);
        s1.setName("张三777");

        StudentDO s2 = new StudentDO();
        s2.setId(2);
        s2.setName("李四777");

        List<StudentDO> dos = Lists.newArrayList();
        dos.add(s1);
        dos.add(s2);

        studentDAO.updateBatch(dos);
    }

    @Rollback(value = false)
    @Test
    public void test_updateScoreByIds() {
        Integer score = 65;
        List<Integer> ids = Lists.newArrayList(1,2,3,8);
        int rowNums = studentDAO.updateScoreByIds(score, ids);
        Assert.isTrue(rowNums == 4, DEFAULT_ERROR_DESCRIBE);
    }

    // 计算总的页数
    private int calculatePageNum(int totalRecords, int pageSize) {
        return (totalRecords % pageSize) == 0 ? (totalRecords / pageSize) :  (totalRecords / pageSize) + 1;
    }

    // 计算分页的起始位置
    private int calculateStart(int pageIndex, int pageSize) {
        return (pageIndex - 1) * pageSize;
    }

     /**
     * mybatis使用场景2：能够进行事务处理
     */

    /**
     * mybatis使用场景3：mybatis映射XML的标签元素使用
     * 如<select/>、<update/>等等
     * 1）All the statements have unique id （XML中的所有语句都有唯一标识的id）
     *
     * 2）All these Mapped SQL statements are resided within the element named<mapper>.
     * This element contains an attribute called ‘namespace’（所有的元素都位于<mapper>元素中，该元素有一个属性为namespace）
     *
     * 3）resultMaps：It is the most important and powerful elements in MyBatis.
     * The results of SQL SELECT statements are mapped to Java objects (beans/POJO).
     * （resultMaps包含表字段与POJO属性的映射）
     *
     * 4）<foreach collection="xxx"> 接收值的方式
     *   4.1）若参数传递的是Map，collection标签属性值为Map中的key，如collection="ids"
     *   4.2）若参数传递的是List，collection值填写为 collection="collection或list"
     *   4.3）若参数传递的是Array，collection的值写为collection="array"
     *
     * 5）foreach元素的属性主要有item，index，collection，open，separator，close。
     * （源码 DefaultSqlSession#wrapCollection有指定 具体值，如"collection"、"list"）
     * item：集合中元素迭代时的别名，该参数为必选。
     * index：在list和数组中,index是元素的序号，在map中，index是元素的key，该参数可选（就是取数组的下标或Map的key处理）
     * open：foreach代码的开始符号，一般是(和close=")"合用。常用在in(),values()时。该参数可选
     * separator：元素之间的分隔符，例如在in()的时候，separator=","会自动在元素中间用“,“隔开，避免手动输入逗号导致sql错误，如in(1,2,)这样。该参数可选。
     * close: foreach代码的关闭符号，一般是)和open="("合用。常用在in(),values()时。该参数可选。
     * collection: 要做foreach的对象，
     *
     * 6）<set>标签使用
     * MyBatis在生成update语句时若使用if标签，如果前面的if没有执行，则可能导致有多余逗号的错误。
     * 使用set标签可以将动态的配置SET 关键字，和剔除追加到条件末尾的任何不相关的逗号。
     * 如结尾为：（SQL语句中会产生SET关键字，并且会把末尾的逗号去掉）
     * <set>
     *   <if test="score != null">
     * 		`score` = #{score},
     * 	 </if>
     *   <if test="item.age != null">
     * 	    `age` = #{item.age},
     *   </if>
     * <set/>
     */
    @Rollback(value = false)
    @Test
    public void test_mybatis_tag() {

        List<UserAttrDO> oldUserAttrs = userAttrDAO.getAllUserAttr();

        UserAttrDO userAttrDO = new UserAttrDO();
        userAttrDO.setName("李四");
        userAttrDO.setFirstAttr1("ff11");
        userAttrDO.setFirstAttr2("ff22");
        userAttrDO.setSecondAttr1("ss11");
        userAttrDO.setSecondAttr2("ss22");

        // <foreach index="index"> 传入的是list，index为列表的下标，从0开始
        userAttrDAO.insertUserAttrInputList(userAttrDO);

        // <foreach index="index"> 传入的是Map时，index为Map的key值，key设置什么，${index}取到的值就是什么，
        // index="index" 若只声明，没有使用${index}提取值的话，没啥影响的
        userAttrDAO.insertUserAttrInputMap(userAttrDO);

        List<UserAttrDO> newUserAttrs = userAttrDAO.getAllUserAttr();
        Assert.isTrue(newUserAttrs.size() == (oldUserAttrs.size() + 1), DEFAULT_ERROR_DESCRIBE);
    }


    /**
     * mybatis使用场景4：能够对BaseDAO进行公共处理
     * 1）对公共字段进行处理，如：enterpriseNo
     * 2）对创建时间、修改时间进行设置
     * 3）提供字段，判断是否要对enterpriseNo等做公共处理
     * 4）看下能否拿到表中的字段（若表中有enterpriseNo，强制填入对应值）
     */
}
