package mybatis.mysql.testV2.dao;

import mybatis.mysql.testV2.common.IBaseDAO;
import mybatis.mysql.testV2.entity.StudentDO;

/**
 * @Author chenSy
 * @Date 2023/02/20 20:20
 * @Description
 */
public interface IStudentDAO extends IBaseDAO<StudentDO> {
    StudentDO getByStudentId(String studentId);
}
