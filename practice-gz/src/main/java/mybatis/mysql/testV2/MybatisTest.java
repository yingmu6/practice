package mybatis.mysql.testV2;

import com.alibaba.fastjson.JSON;
import mybatis.mysql.testV2.dao.IStudentDAO;
import mybatis.mysql.testV2.entity.StudentDO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author chenSy
 * @Date 2023/02/20 20:18
 * @Description
 */
public class MybatisTest {

    public static void main(String[] args) throws Exception{
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/spring/spring-database-config.xml");
        IStudentDAO studentDAO = (IStudentDAO) applicationContext.getBean("studentDAO");

        getStudent(studentDAO);
//        deleteStudent(studentDAO);
//        updateStudent(studentDAO);
//        addStudent(studentDAO);
    }

    public static void getStudent(IStudentDAO studentDAO) {
        StudentDO studentDO = studentDAO.get(3, "11a");
        System.out.println("getById值：" + JSON.toJSONString(studentDO));

        StudentDO studentDO1 = studentDAO.getByStudentId("3001");
        System.out.println("getByStudentId值：" + JSON.toJSONString(studentDO1));
    }

    public static void addStudent(IStudentDAO studentDAO) {
        StudentDO addDO = new StudentDO();
        addDO.setEnterpriseNo("33a");
        addDO.setName("刘六2");
        addDO.setAge(15);
        addDO.setScore(85);
        addDO.setStudentId("4001");

        studentDAO.save(addDO);
    }

    public static void deleteStudent(IStudentDAO studentDAO) {
        studentDAO.remove(6, "11a");
    }

    public static void updateStudent(IStudentDAO studentDAO) {
        StudentDO studentDO = new StudentDO();
        studentDO.setId(3);
        studentDO.setName("wangwu888");

        studentDAO.update(studentDO);
    }
}
