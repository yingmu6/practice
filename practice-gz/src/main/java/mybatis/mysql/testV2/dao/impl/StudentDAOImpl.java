package mybatis.mysql.testV2.dao.impl;

import mybatis.mysql.testV2.common.BaseDAO;
import mybatis.mysql.testV2.dao.IStudentDAO;
import mybatis.mysql.testV2.entity.StudentDO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author chenSy
 * @Date 2023/02/20 20:21
 * @Description
 */
@Repository("studentDAO")
public class StudentDAOImpl extends BaseDAO<StudentDO> implements IStudentDAO {

    public StudentDAOImpl() {
        super();
    }

    @Override
    public StudentDO getByStudentId(String studentId) {
        Map<String, Object> params = new HashMap<>(3);
        params.put("studentId", studentId);
        return queryForObject(nameSpace + "getByStudentId", params);
    }
}
