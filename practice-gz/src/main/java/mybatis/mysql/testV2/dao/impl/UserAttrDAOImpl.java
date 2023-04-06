package mybatis.mysql.testV2.dao.impl;

import com.google.common.collect.Lists;
import mybatis.mysql.testV2.common.BaseDAO;
import mybatis.mysql.testV2.dao.IUserAttrDAO;
import mybatis.mysql.testV2.entity.UserAttrDO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author chenSy
 * @Date 2023/04/06 23:18
 * @Description
 */
@Repository("userAttrDAO")
public class UserAttrDAOImpl extends BaseDAO<UserAttrDO> implements IUserAttrDAO {

    @Override
    public void insertUserAttr(UserAttrDO userAttrDO) {
        List<String> firstAttr = Lists.newArrayList(userAttrDO.getFirstAttr1(), userAttrDO.getFirstAttr2());
        List<String> secondAttr = Lists.newArrayList(userAttrDO.getSecondAttr1(), userAttrDO.getSecondAttr2());

        Map<String, Object> params = new HashMap<>();
        params.put("name", userAttrDO.getName());
        params.put("firstAttr", firstAttr);
        params.put("secondAttr", secondAttr);
        super.executeInsert(nameSpace + "insertUserAttr", params);
    }

    @Override
    public List<UserAttrDO> getAllUserAttr() {
        return super.queryForList(nameSpace + "getAllUserAttr", null);
    }
}
