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

        Animal animal = (Animal) applicationContext.getBean("animal");
        System.out.println(JSON.toJSONString(animal));

        IStudentDAO studentDAO = (IStudentDAO) applicationContext.getBean("studentDAO");
//        StudentDO studentDO = studentDAO.getById(3);
//        System.out.println("getById值：" + JSON.toJSONString(studentDO));

        StudentDO studentDO1 = studentDAO.getByStudentId("3001");
        System.out.println("getByStudentId值：" + JSON.toJSONString(studentDO1));

        System.in.read();
    }

    /**
     * 问题点：
     * 1）java.lang.AbstractMethodError: org.mybatis.spring.transaction.SpringManagedTransaction.getTimeout()Ljava/lang/Integer
     *    解答：需要mybatis-spring、mybatis、spring版本相匹配
     *         https://www.jianshu.com/p/f329b3403113
     *
     */
}
