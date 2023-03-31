package mybatis.mysql.testV2;

import com.alibaba.fastjson.JSON;
import mybatis.mysql.testV2.dao.IStudentDAO;
import mybatis.mysql.testV2.entity.StudentDO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.basic.Animal;

/**
 * @Author chenSy
 * @Date 2023/02/20 20:18
 * @Description
 */
public class StudentDAOTest {

    public static void main(String[] args) throws Exception{
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/spring/spring-database-config.xml");

        IStudentDAO studentDAO = (IStudentDAO) applicationContext.getBean("studentDAO");
        StudentDO studentDO = studentDAO.getById(3);
        System.out.println("getById值：" + JSON.toJSONString(studentDO));

        StudentDO studentDO1 = studentDAO.getByStudentId("3001");
        System.out.println("getByStudentId值：" + JSON.toJSONString(studentDO1));

        StudentDO addDO = new StudentDO();
        addDO.setName("刘六");
        addDO.setAge(15);
        addDO.setScore(85);
        addDO.setStudentId(4001);

        studentDAO.add(addDO);
    }
}
