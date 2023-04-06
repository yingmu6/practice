package mybatis.mysql.testV2.dao;

import mybatis.mysql.testV2.common.IBaseDAO;
import mybatis.mysql.testV2.condition.PageQueryStudentCondition;
import mybatis.mysql.testV2.entity.StudentDO;

import java.util.List;

/**
 * @Author chenSy
 * @Date 2023/02/20 20:20
 * @Description
 */
public interface IStudentDAO extends IBaseDAO<StudentDO> {

    StudentDO getByStudentId(String studentId);

    int queryCountByCondition(PageQueryStudentCondition condition);

    List<StudentDO> queryByCondition(PageQueryStudentCondition condition);

    int updateScoreByIds(Integer score, List<Integer> ids);
}
