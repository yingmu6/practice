package com.csy.mybatis.mysql.testV2.dao.impl;

import com.csy.mybatis.mysql.testV2.condition.PageQueryStudentCondition;
import com.csy.mybatis.mysql.testV2.dao.BaseDAO;
import com.csy.mybatis.mysql.testV2.dao.IStudentDAO;
import com.csy.mybatis.mysql.testV2.entity.StudentDO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author chenSy
 * @Date 2023/02/20 20:21
 * @Description
 */
@Repository("studentDAO")
public class StudentDAOImpl extends BaseDAO<StudentDO> implements IStudentDAO {

    public StudentDAOImpl() {
        super.setCheckEnterpriseNo(false);
    }

    @Override
    public StudentDO getByStudentId(String studentId) {
        Map<String, Object> params = new HashMap<>(3);
        params.put("studentId", studentId);
        return queryForObject(nameSpace + "getByStudentId", params);
    }

    @Override
    public int queryCountByCondition(PageQueryStudentCondition condition) {
        return super.queryCount(nameSpace + "queryCountByCondition", condition);
    }

    @Override
    public List<StudentDO> queryByCondition(PageQueryStudentCondition condition) {
        return queryForList(nameSpace + "queryByCondition", condition);
    }

    @Override
    public int updateScoreByIds(Integer score, List<Integer> ids) {
        // Map形式设置列表（<foreach collection="ids">）
        Map<String, Object> params = new HashMap<>();
        params.put("score", score);
        params.put("ids", ids);
        return super.executeUpdate(nameSpace + "updateScoreByIds", params);

        // 传递List形式（<foreach collection="list">）
//        return super.executeUpdate(nameSpace + "updateScoreByIds", ids);

        // 传递Array形式（<foreach collection="array">>
//        return super.executeUpdate(nameSpace + "updateScoreByIds", ids.toArray());

    }
}
